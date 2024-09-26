package org.chat.backend.services.credentials;

public class CredentialsView {

    private Long id = 0L;

    private String usertag = "";

    private String username = "";

    private String password = "";

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

    public CredentialsView setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CredentialsModel toModel() {
        return new CredentialsModel()
                .setUsertag(usertag)
                .setUsername(username);
    }

}
