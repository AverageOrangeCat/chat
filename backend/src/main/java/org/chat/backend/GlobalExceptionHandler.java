package org.chat.backend;

import org.chat.backend.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GlobalExceptionResponse handleNotFoundException(NotFoundException exception) {
        return new GlobalExceptionResponse()
            .setHttpStatus(HttpStatus.NOT_FOUND)
            .setMessage("Could not find: " + exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GlobalExceptionResponse handleOtherException(Exception exception) {
        return new GlobalExceptionResponse()
            .setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
            .setMessage("An error occured: " + exception.getMessage());
    }

    // Send not found resource requests to the frontend and let it deal with it

    @ExceptionHandler(NoResourceFoundException.class)
    public ModelAndView handleNoResourceFoundException(NoResourceFoundException exception) {
        return new ModelAndView("forward:/index.html");
    }

}
