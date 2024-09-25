package org.chat.backend.services.credentials;

public class CredentialsCreateModel {

    public final String usertag;

    public final String username;

    public final String password;

    // Implemented for jacksons deserialization actions

    public CredentialsCreateModel() {
        this.usertag = "";
        this.username = "";
        this.password = "";
    }

    public CredentialsCreateModel(String usertag, String username, String password) {
        this.usertag = usertag;
        this.username = username;
        this.password = password;
    }

    public CredentialsCreateView toView() {
        return new CredentialsCreateView(usertag, username, password);
    }

}
