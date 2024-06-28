package fr.esgi.calendrier_APP_BR.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErreurController implements ErrorController {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleOtherExceptions(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMessage", "Une erreur est survenue: " + ex.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @RequestMapping("/error")
    public String handleError() {
        return "error"; // Return the name of your error view
    }

}
