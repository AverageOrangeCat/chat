package org.chat.backend.services.credentials;

import org.chat.backend.services.Model;

public class CredentialsCreateModel implements Model<CredentialsCreateView> {

    public final String usertag;

    public final String username;

    public final String password;

    public CredentialsCreateModel(String usertag, String username, String password) {
        this.usertag = usertag;
        this.username = username;
        this.password = password;
    }

    @Override
    public CredentialsCreateView toView() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toView'");
    }

}
