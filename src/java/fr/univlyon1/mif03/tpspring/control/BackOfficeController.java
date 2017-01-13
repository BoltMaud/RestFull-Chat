package fr.univlyon1.mif03.tpspring.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by A626200 on 02/11/2016.
 */
@Controller
public class BackOfficeController {

    @RequestMapping("/back-office")
    public String getBackend() {
        return "/back-office";
    }

}
