package org.chat.backend.services.credentials;

public class CredentialsModel {

    public final String usertag;

    public final String username;

    // Implemented for jacksons deserialization actions

    public CredentialsModel() {
        this.usertag = "";
        this.username = "";
    }

    public CredentialsModel(String usertag, String username) {
        this.usertag = usertag;
        this.username = username;
    }

}
