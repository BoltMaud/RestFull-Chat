package fr.univlyon1.mif03.tpspring.control;

import fr.univlyon1.mif03.tpspring.beans.SalonRepository;
import fr.univlyon1.mif03.tpspring.beans.UserRepository;
import fr.univlyon1.mif03.tpspring.domain.Message;
import fr.univlyon1.mif03.tpspring.domain.User;
import fr.univlyon1.mif03.tpspring.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by A626200 on 30/11/2016.
 */
@Controller
@RequestMapping("/saloon")
public class SalonController extends ErrorController {

    @Autowired
    private SalonRepository salonRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity getSaloon(@RequestParam String salon) {
        if(salonRepository.getSaloon(salon) != null) {
            return new ResponseEntity<>(salonRepository.getSaloon(salon), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * adds a salon
     * @param salon
     * @return 1 if success
     */
    @PostMapping
    public ResponseEntity add(@RequestParam String salon) {
        salonRepository.addSaloon(salon);
        return new ResponseEntity<>(salonRepository.getSaloon(salon), HttpStatus.CREATED);
    }

    /**
     * Get the list of message for a saloon
     *
     * @return list
     */
    @RequestMapping(value = "/{salon}/messages", method = RequestMethod.GET)
    public ResponseEntity getListMessages(@PathVariable String salon,
                                          HttpServletRequest request) throws IOException {
        salonRepository.addSaloon(salon);

        if ( salon.length() > 0 && String.valueOf(salonRepository.getSaloon(salon).getVersion())
                .equals(request.getHeader("If-modified-since"))
                && salonRepository.getNbMessagesSaloon(salon) != 0) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Last-modified",String.valueOf(salonRepository.getSaloon(salon).getVersion()) );
            ResponseEntity responseEntity = new ResponseEntity<>(salonRepository.getMessagesSaloon(salon), headers, HttpStatus.ACCEPTED);
            return responseEntity;
        }
    }

    /**
     * list for interface jsp
     * @param salon
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/{salon}/messages", produces = {"text/html"})
    public String getListMessagesWithView(@PathVariable String salon,
                                          HttpServletRequest request,
                                          HttpServletResponse response,
                                          Model model)
    {
        salonRepository.addSaloon(salon);
        if(   salon != null && String.valueOf(salonRepository.getSaloon(salon).getVersion())
                .equals(request.getHeader("If-modified-since"))
                && salonRepository.getNbMessagesSaloon(salon) != 0) {
            response.setStatus(304);
            return null;
        }
        else {
            response.addHeader("Last-modified",
                    String.valueOf(salonRepository.getSaloon(salon).getVersion()));
            model.addAttribute("salon", salon);
            model.addAttribute("listMessages", salonRepository.getMessagesSaloon(salon) );
            return "/display";
        }
    }

    /**
     * Gets messages from a saloon after a i index
     *
     * @param salon the saloon name
     * @param index the message index
     * @return
     */
    @RequestMapping(path = "/{salon}/messages/{index}", method = RequestMethod.GET)
    public ResponseEntity getMessagesFromSalon(
            @PathVariable String salon,
            @PathVariable Integer index) {

        if (salonRepository.getSaloon(salon) != null) {
            List<Message> m = salonRepository.getMessagesAfterISaloon(salon, index);
            return new ResponseEntity<>(m, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private HttpStatus addMessagePrivate(String salon, String message, String pseudo) {
        User u = userRepository.getUser(pseudo);
        if (u != null) {
            salonRepository.addSaloon(salon);
            salonRepository.addMessage(salon, new Message(message, u));
            u.addSaloons(salon);
            return HttpStatus.ACCEPTED;

        } else {
            return HttpStatus.UNAUTHORIZED;
        }
    }

    /**
     * Add a message to a given saloon only if user is allowed
     *
     * @param message
     */
    @PostMapping(value = "/{salon}/message",  produces = {"text/html"})
    public String addMessageWithView(@RequestParam String pseudo,
                                     @RequestParam String message,
                                     @PathVariable String salon) throws GlobalException {

        if(addMessagePrivate(salon, message, pseudo) == HttpStatus.ACCEPTED) {
            salonRepository.getSaloon(salon).setNewVersion();
            return "redirect:/saloon/"+salon+"/messages";
        }
        else {
            throw new GlobalException("User is not allowed");
        }
    }
    /**
     * Add a message to a given saloon only if user is allowed
     * @param message
     */
    @PostMapping(value = "/{salon}/message")
    public ResponseEntity addMessage(@RequestParam String pseudo,
                                     @RequestParam String message,
                                     @PathVariable String salon)  {
        salonRepository.getSaloon(salon).setNewVersion();
        return new ResponseEntity(addMessagePrivate(salon, message, pseudo));
    }

    /**
     * gets the names of the saloons
     * @return
     */
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ResponseEntity getListOfSaloons() {
        return new ResponseEntity<>(salonRepository.getSaloonsNames(), HttpStatus.OK);
    }

    /**
     * delete a saloon
     */
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/{saloon}", method = RequestMethod.DELETE)
    public ResponseEntity<Serializable> delete(@PathVariable String saloon) throws GlobalException {
        try {
            salonRepository.deleteSaloon(saloon);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new GlobalException("Saloon does not exist");
        }
    }
}
