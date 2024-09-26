package org.chat.backend.services.credentials;

public class CredentialsCreateView {

    private String usertag = "";

    private String username = "";

    private String password = "";

    public CredentialsCreateView setUsertag(String usertag) {
        this.usertag = usertag;
        return this;
    }

    public String getUsertag() {
        return usertag;
    }

    public CredentialsCreateView setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CredentialsCreateView setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CredentialsCreateModel toModel() {
        return new CredentialsCreateModel()
                .setUsertag(usertag)
                .setUsername(username)
                .setPassword(password);
    }

}
