package org.chat.backend;

import org.chat.backend.exceptions.BearTokenNotProvidedException;
import org.chat.backend.exceptions.CredentialsNotFoundException;
import org.chat.backend.exceptions.InvalidLoginAttemptException;
import org.chat.backend.exceptions.NotFoundException;
import org.chat.backend.exceptions.SessionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidLoginAttemptException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public GlobalExceptionResponse handleInvalidLoginAttemptException(
            InvalidLoginAttemptException exception) {
        return new GlobalExceptionResponse()
                .setHttpStatus(HttpStatus.UNAUTHORIZED)
                .setMessage("Login attempt failed");
    }

    @ExceptionHandler(BearTokenNotProvidedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GlobalExceptionResponse handleBearTokenNotProvidedException(
            BearTokenNotProvidedException exception) {
        return new GlobalExceptionResponse()
                .setHttpStatus(HttpStatus.BAD_REQUEST)
                .setMessage("Bear-Token is missing");
    }

    @ExceptionHandler(SessionNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public GlobalExceptionResponse handleSessionNotFoundException(
            SessionNotFoundException exception) {
        return new GlobalExceptionResponse()
                .setHttpStatus(HttpStatus.UNAUTHORIZED)
                .setMessage("Could not find session: " + exception.getMessage());
    }

    @ExceptionHandler(CredentialsNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GlobalExceptionResponse handleCredentialsNotFoundException(
            CredentialsNotFoundException exception) {
        return new GlobalExceptionResponse()
                .setHttpStatus(HttpStatus.NOT_FOUND)
                .setMessage("Could not find credentials: " + exception.getMessage());
    }

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

}
