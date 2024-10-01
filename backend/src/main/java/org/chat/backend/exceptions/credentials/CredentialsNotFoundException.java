package org.chat.backend.exceptions.credentials;

public class CredentialsNotFoundException extends Exception {

    public CredentialsNotFoundException() {
        super("Not specified");;
    }

    public CredentialsNotFoundException(String message) {
        super(message);
    }

}
