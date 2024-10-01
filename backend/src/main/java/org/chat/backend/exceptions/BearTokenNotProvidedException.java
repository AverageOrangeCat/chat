package org.chat.backend.exceptions;

public class BearTokenNotProvidedException extends Exception {

    public BearTokenNotProvidedException() {
        super("Not specified");;
    }

    public BearTokenNotProvidedException(String message) {
        super(message);
    }

}
