package org.chat.backend.services.credentials;

public class PasswordView {

    private String passwordSalt = "";

    private String passwordHash = "";

    public PasswordView setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
        return this;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public PasswordView setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        return this;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

}
