package org.chat.backend.services.credentials;

public class CredentialsCreateView {

    private String usertag = "";

    private String username = "";

    private String passwordSalt = "";

    private String passwordHash = "";

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

    public CredentialsCreateView setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
        return this;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public CredentialsCreateView setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        return this;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

}
