package org.chat.backend.exceptions;

public class DatabaseException extends Exception {
    
    public DatabaseException() {
        super("Not specified");;
    }

    public DatabaseException(String message) {
        super(message);
    }

}
