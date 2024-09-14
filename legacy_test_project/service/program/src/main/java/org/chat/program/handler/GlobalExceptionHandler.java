package org.chat.program.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOtherExceptions(Exception exception) {
        return ResponseEntity.internalServerError()
            .body("An error occurred: " + exception.getMessage());
    }

}
