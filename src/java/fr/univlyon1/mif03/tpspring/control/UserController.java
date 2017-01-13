package fr.univlyon1.mif03.tpspring.control;

import fr.univlyon1.mif03.tpspring.beans.UserRepository;
import fr.univlyon1.mif03.tpspring.domain.User;
import fr.univlyon1.mif03.tpspring.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by A626200 on 26/11/2016.
 */
@Controller
@RequestMapping("/user")
public class UserController extends ErrorController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Add an user allowed to use the chat
     *
     * @param username the name of the user
     */
    @PostMapping
    public ResponseEntity<Serializable> add(@RequestParam String username) throws GlobalException {
        if(!userRepository.userExist(username)) {
            User user = new User(UserRepository.idNewUser, username);
            if(userRepository.addUser(user)) {
                return new ResponseEntity<>(user, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>("User already exist", HttpStatus.CONFLICT);
        }
    }

    /**
     * post for interface jsp
     * @param username
     * @return
     * @throws GlobalException
     */
    @PostMapping(produces = {"text/html"})
    public String addNoRest(@RequestParam String username) throws GlobalException {
        if(!userRepository.userExist(username)) {
            User user = new User(UserRepository.idNewUser, username);
            if(userRepository.addUser(user)) {
                return "redirect:/";
            }
            throw new GlobalException("User not added");
        } else {
            throw new GlobalException("User already exist");
        }
    }


    /**
     * get the list of users who used the app
     *
     * @param
     * @return
     */
    @GetMapping(value = "/list")
    public ResponseEntity<Collection<User>> getList() {
        return new ResponseEntity<>(userRepository.getUserCollection(), HttpStatus.OK);
    }

    /**
     * get a user by Id
     *
     * @param idUser
     * @return
     */
    @GetMapping(value = "/{idUser}")
    public ResponseEntity<Serializable> get(@PathVariable int idUser) {
        if (userRepository.getUser(idUser) != null) {
            return new ResponseEntity<>(userRepository.getUser(idUser), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * update a user
     *
     * @param idUser
     * @return
     */
    @RequestMapping(value = "/{idUser}", method = RequestMethod.PATCH)
    public ResponseEntity<Serializable> update(@PathVariable int idUser, @RequestParam String newUser) {
        if (userRepository.getUser(idUser) != null && userRepository.userExist(newUser)) {
            userRepository.getUser(idUser).setName(newUser);
            return new ResponseEntity<>(userRepository.getUser(idUser), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("User does not match to id", HttpStatus.CONFLICT);
    }

    /**
     * gets the list of saloons of a user
     *
     * @param idUser
     * @return
     */
    @GetMapping(value = "/{idUser}/saloons")
    public ResponseEntity getsalons(@PathVariable int idUser) {
        if(this.userRepository.getUser(idUser) != null)
            return new ResponseEntity<>(userRepository.getUser(idUser).getSaloons(), HttpStatus.OK);
        return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
    }

    /**
     * delete a user by id
     *
     * @param idUser
     * @return 1 if success
     */
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/{idUser}", method = RequestMethod.DELETE)
    public ResponseEntity<Serializable> delete(@PathVariable int idUser)  {
        if (userRepository.deleteUser(idUser)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
        }
    }


}
