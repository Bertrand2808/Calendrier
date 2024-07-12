package fr.esgi.calendrier_APP_BR.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErreurController implements ErrorController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ModelAndView handleValidationExceptions(MethodArgumentNotValidException ex) {
        ModelAndView modelAndView = new ModelAndView();
        StringBuilder errorMessage = new StringBuilder("Erreurs de validation: ");

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ");
        }

        modelAndView.addObject("errorMessage", errorMessage.toString());
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ModelAndView handleConstraintViolationException(ConstraintViolationException ex) {
        ModelAndView modelAndView = new ModelAndView();
        StringBuilder errorMessage = new StringBuilder("Erreurs de validation: ");

        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errorMessage.append(violation.getPropertyPath()).append(": ").append(violation.getMessage()).append("; ");
        }

        modelAndView.addObject("errorMessage", errorMessage.toString());
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleOtherExceptions(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMessage", "Une erreur est survenue: " + ex.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @RequestMapping("/error")
    public String handleError() {
        return "error";
    }
}