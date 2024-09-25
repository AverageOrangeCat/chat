package org.chat.backend.services.credentials;

public class CredentialsView {

    public final Long id;

    public final String usertag;

    public final String username;

    public final String password;

    public CredentialsView(
            Long id,
            String usertag,
            String username,
            String password) {
        this.id = id;
        this.usertag = usertag;
        this.username = username;
        this.password = password;
    }

    public CredentialsModel toModel() {
        return new CredentialsModel(usertag, username);
    }

}
