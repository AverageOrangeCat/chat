package org.chat.backend.services.credentials;

public class CredentialsCreateView {

    private String usertag = "";

    private String username = "";

    private byte[] passwordSalt = new byte[0];

    private byte[] passwordHash = new byte[0];

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

    public CredentialsCreateView setPasswordSalt(byte[] passwordSalt) {
        this.passwordSalt = passwordSalt;
        return this;
    }

    public byte[] getPasswordSalt() {
        return passwordSalt;
    }

    public CredentialsCreateView setPasswordHash(byte[] passwordHash) {
        this.passwordHash = passwordHash;
        return this;
    }

    public byte[] getPasswordHash() {
        return passwordHash;
    }

}
