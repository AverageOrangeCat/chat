package org.chat.backend;

import org.springframework.http.HttpStatus;

public class GlobalExceptionResponse {

    private Integer statusCode = 500;
    private String message = "";

    public GlobalExceptionResponse setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public GlobalExceptionResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getMessage() {
        return message;
    }

    // Special method required in 'GlobalExceptionHandler.java'

    public GlobalExceptionResponse setHttpStatus(HttpStatus httpStatus) {
        return setStatusCode(httpStatus.value());
    }

}
