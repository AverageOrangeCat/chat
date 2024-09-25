package org.chat.backend.exceptions;

public class SessionNotFoundException extends Exception {

    public SessionNotFoundException() {
        super();
    }

    public SessionNotFoundException(String message) {
        super(message);
    }

}
