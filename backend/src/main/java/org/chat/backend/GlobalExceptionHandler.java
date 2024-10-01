package org.chat.backend;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.chat.backend.exceptions.BearTokenNotProvidedException;
import org.chat.backend.exceptions.DatabaseException;
import org.chat.backend.exceptions.InvalidLoginAttemptException;
import org.chat.backend.exceptions.NotFoundException;
import org.chat.backend.exceptions.credentials.CredentialsAlreadyExistsException;
import org.chat.backend.exceptions.credentials.CredentialsNotFoundException;
import org.chat.backend.exceptions.session.SessionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchAlgorithmException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GlobalExceptionResponse handleNoSuchAlgorithmException(
            NoSuchAlgorithmException exception) {
        return new GlobalExceptionResponse()
                .setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .setMessage("Invalid hashing algorithm provided");
    }

    @ExceptionHandler(UnsupportedEncodingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GlobalExceptionResponse handleUnsupportedEncodingException(
            UnsupportedEncodingException exception) {
        return new GlobalExceptionResponse()
                .setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .setMessage("Invalid encoding provided");
    }

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

    @ExceptionHandler(CredentialsAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public GlobalExceptionResponse handleCredentialsAlreadyExistsException(
            CredentialsAlreadyExistsException exception) {
        return new GlobalExceptionResponse()
                .setHttpStatus(HttpStatus.CONFLICT)
                .setMessage("Credentials already exists: " + exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GlobalExceptionResponse handleNotFoundException(NotFoundException exception) {
        return new GlobalExceptionResponse()
                .setHttpStatus(HttpStatus.NOT_FOUND)
                .setMessage("Could not find: " + exception.getMessage());
    }

    @ExceptionHandler(DatabaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GlobalExceptionResponse handleDatabaseException(DatabaseException exception) {
        return new GlobalExceptionResponse()
                .setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .setMessage("An database error occured: " + exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GlobalExceptionResponse handleOtherException(Exception exception) {
        return new GlobalExceptionResponse()
                .setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .setMessage("An error occured: " + exception.getMessage());
    }

}
