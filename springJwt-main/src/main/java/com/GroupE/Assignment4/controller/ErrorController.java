package com.GroupE.Assignment4.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGlobalError(Exception exception) {
        HttpStatus statusCode;

        if (exception instanceof NoHandlerFoundException) {
            statusCode = HttpStatus.NOT_FOUND;
        } else {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("statusCode", statusCode.value());
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }
}
