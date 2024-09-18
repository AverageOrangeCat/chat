package org.chat.backend.services.credentials;

import org.chat.backend.services.View;

public class CredentialsView implements View<CredentialsModel> {

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

    @Override
    public CredentialsModel toModel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toModel'");
    }

}
