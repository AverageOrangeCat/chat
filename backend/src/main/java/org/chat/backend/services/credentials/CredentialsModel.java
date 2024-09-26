package org.chat.backend.services.credentials;

public class CredentialsModel {

    private String usertag = "";

    private String username = "";

    public CredentialsModel setUsertag(String usertag) {
        this.usertag = usertag;
        return this;
    }

    public String getUsertag() {
        return usertag;
    }

    public CredentialsModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CredentialsView toView() {
        return new CredentialsView()
                .setUsertag(usertag)
                .setUsername(username);
    }

}
