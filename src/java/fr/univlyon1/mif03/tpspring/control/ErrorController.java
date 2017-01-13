package fr.univlyon1.mif03.tpspring.control;

import fr.univlyon1.mif03.tpspring.exception.GlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Maud on 04/12/2016.
 */
public class ErrorController {

    /**
     * Handler Exception
     *
     * @param ex
     * @param model
     * @return
     */
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(GlobalException.class)
    public ModelAndView error(Exception ex, ModelAndView model) {
        model.addObject("errorMsg", ex);
        model.setViewName("/error");
        return model;
    }
}
