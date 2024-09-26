package org.chat.backend.services.credentials;

public class CredentialsView {

    private Long id = 0L;

    private String usertag = "";

    private String username = "";

    private byte[] passwordSalt = new byte[0];

    private byte[] passwordHash = new byte[0];

    public CredentialsView setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getId() {
        return id;
    }

    public CredentialsView setUsertag(String usertag) {
        this.usertag = usertag;
        return this;
    }

    public String getUsertag() {
        return usertag;
    }

    public CredentialsView setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CredentialsView setPasswordSalt(byte[] passwordSalt) {
        this.passwordSalt = passwordSalt;
        return this;
    }

    public byte[] getPasswordSalt() {
        return passwordSalt;
    }

    public CredentialsView setPasswordHash(byte[] passwordHash) {
        this.passwordHash = passwordHash;
        return this;
    }

    public byte[] getPasswordHash() {
        return passwordHash;
    }

    public CredentialsModel toModel() {
        return new CredentialsModel()
                .setUsertag(usertag)
                .setUsername(username);
    }

}
