package org.chat.backend.exceptions;

public class CredentialsNotFoundException extends Exception {

    public CredentialsNotFoundException() {
        super();
    }

    public CredentialsNotFoundException(String message) {
        super(message);
    }

}
