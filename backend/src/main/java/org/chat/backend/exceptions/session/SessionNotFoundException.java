package org.chat.backend.exceptions.session;

public class SessionNotFoundException extends Exception {

    public SessionNotFoundException() {
        super("Not specified");;
    }

    public SessionNotFoundException(String message) {
        super(message);
    }

}
