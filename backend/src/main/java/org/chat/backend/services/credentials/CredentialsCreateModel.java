package org.chat.backend.services.credentials;

public class CredentialsCreateModel {

    private String usertag = "";

    private String username = "";

    private String password = "";

    public CredentialsCreateModel setUsertag(String usertag) {
        this.usertag = usertag;
        return this;
    }

    public String getUsertag() {
        return usertag;
    }

    public CredentialsCreateModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CredentialsCreateModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CredentialsCreateView toView() {
        return new CredentialsCreateView()
                .setUsertag(usertag)
                .setUsername(username)
                .setPassword(password);
    }

}
