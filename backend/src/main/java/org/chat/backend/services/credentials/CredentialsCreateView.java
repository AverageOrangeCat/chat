package org.chat.backend.services.credentials;

import org.chat.backend.services.View;

public class CredentialsCreateView implements View<CredentialsCreateModel> {

    public final String usertag;

    public final String username;

    public final String password;

    public CredentialsCreateView(String usertag, String username, String password) {
        this.usertag = usertag;
        this.username = username;
        this.password = password;
    }

    @Override
    public CredentialsCreateModel toModel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toModel'");
    }

}
