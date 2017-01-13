package fr.univlyon1.mif03.tpspring.control;

import fr.univlyon1.mif03.tpspring.beans.SalonRepository;
import fr.univlyon1.mif03.tpspring.domain.Message;
import fr.univlyon1.mif03.tpspring.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * Created by A626200 on 30/11/2016.
 */
@Controller
@RequestMapping("/message")
public class MessageController extends ErrorController {

    @Autowired
    private SalonRepository salonRepository;

    /**
     * get instance of i message
     *
     * @param salon     string
     * @param idMessage int
     * @return Message
     */
    @GetMapping(value = "/{salon}/{idMessage}")
    public ResponseEntity<Serializable> get(@PathVariable String salon, @PathVariable Integer idMessage) {
            if(this.salonRepository.getSaloon(salon) != null)
                    if(this.salonRepository.getSaloon(salon).getMessage(idMessage) == null)
                        return new ResponseEntity<>("Message not found", HttpStatus.NOT_FOUND);
                    else
                        return new ResponseEntity<>(this.salonRepository.getSaloon(salon).getMessage(idMessage), HttpStatus.OK);
            else
                return new ResponseEntity<>("Saloon not found", HttpStatus.NOT_FOUND);
    }

    /**
     * update last message
     *
     * @return 1 if success
     */
    @RequestMapping(value = "/{salon}/{idMessage}", method = RequestMethod.PATCH)
    public ResponseEntity<Serializable> update(@PathVariable String salon,
                                       @PathVariable int idMessage,
                                       @RequestParam() String messagetext) {

        if(salonRepository.getMessagesSaloon(salon) == null)
            return new ResponseEntity<>("Saloon does not exist", HttpStatus.NOT_FOUND);

        Message last = salonRepository
                .getMessagesSaloon(salon)
                .get(salonRepository.getNbMessagesSaloon(salon) - 1);

        if (last.getId() == idMessage) {
            last.setText(messagetext);
            salonRepository.getSaloon(salon).setNewVersion();
            return new ResponseEntity<>(last, HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<>("Message is not the last", HttpStatus.FORBIDDEN);
        }
    }

    /**
     * delete a message
     *
     * @return 1 if success
     */
    @RequestMapping(value = "/{salon}/{idMessage}", method = RequestMethod.DELETE)
    public ResponseEntity<Serializable> delete(@PathVariable String salon,
                                       @PathVariable int idMessage) throws GlobalException {
        if(salonRepository.getMessagesSaloon(salon) == null)
            return new ResponseEntity<>("Saloon does not exist", HttpStatus.NOT_FOUND);

        Message last = salonRepository.getMessagesSaloon(salon).get(salonRepository.getNbMessagesSaloon(salon) - 1);

        if (last.getId() == idMessage) {
            salonRepository.getSaloon(salon).deleteMessage(last);
            salonRepository.getSaloon(salon).setNewVersion();
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<>("Message is not the last", HttpStatus.FORBIDDEN);
        }
    }


}
