package org.eas.controller;

import org.eas.exception.NoSuchUserException;
import org.eas.exception.NotEnoughMoneyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

/**
 * @author eas
 */
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = {NoSuchElementException.class, NoSuchUserException.class} )
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void notFound() {
    }

    @ExceptionHandler(value = {NotEnoughMoneyException.class} )
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String notFound(NotEnoughMoneyException e) {
        return "User " + e.getUserNumber() + "has no enough money";
    }
}
