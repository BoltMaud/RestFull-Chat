package fr.univlyon1.mif03.tpspring.control;

import fr.univlyon1.mif03.tpspring.beans.UserRepository;
import fr.univlyon1.mif03.tpspring.domain.LoginForm;
import fr.univlyon1.mif03.tpspring.domain.Message;
import fr.univlyon1.mif03.tpspring.domain.User;
import fr.univlyon1.mif03.tpspring.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by A626200 on 26/11/2016.
 */
@Controller
public class FrontController extends ErrorController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String index() {
        return "redirect:/static/html/index.html";
    }

    /**
     * Login method called when a user try to log
     *
     * @return
     */
    @RequestMapping("/interface")
    public ModelAndView login(@RequestParam String pseudo,
                        @RequestParam String saloon,
                        ModelAndView model) throws GlobalException {
        if (!userRepository.userExist(pseudo)) {
            throw new GlobalException("This user is not allowed to login");
        }
        model.addObject("username", pseudo);
        model.addObject("saloon", saloon);
        model.setViewName("/interface");
        return model;
    }

    /**
     * Logout a user
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        //session.invalidate();
        // Not needed anymore because it's stateless
        return "redirect:/";
    }
}
