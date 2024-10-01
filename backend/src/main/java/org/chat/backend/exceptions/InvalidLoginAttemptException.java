package org.chat.backend.exceptions;

public class InvalidLoginAttemptException extends Exception {
    
    public InvalidLoginAttemptException() {
        super("Not specified");;
    }

    public InvalidLoginAttemptException(String message) {
        super(message);
    }

}
