package org.chat.backend.services.credentials;

public class CredentialsCreateView {

    public final String usertag;

    public final String username;

    public final String password;

    public CredentialsCreateView(String usertag, String username, String password) {
        this.usertag = usertag;
        this.username = username;
        this.password = password;
    }

}
