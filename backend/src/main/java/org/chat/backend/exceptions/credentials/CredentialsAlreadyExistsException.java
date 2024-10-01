package org.chat.backend.exceptions.credentials;

public class CredentialsAlreadyExistsException extends Exception {
    
    public CredentialsAlreadyExistsException() {
        super("Not specified");
    }

    public CredentialsAlreadyExistsException(String message) {
        super(message);
    }

}
