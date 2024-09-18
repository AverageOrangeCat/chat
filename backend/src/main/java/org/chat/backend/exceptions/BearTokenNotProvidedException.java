package org.chat.backend.exceptions;

public class BearTokenNotProvidedException extends Exception {

    public BearTokenNotProvidedException() {
        super();
    }

    public BearTokenNotProvidedException(String message) {
        super(message);
    }

}
