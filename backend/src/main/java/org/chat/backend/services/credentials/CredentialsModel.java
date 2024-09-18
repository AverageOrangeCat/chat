package org.chat.backend.services.credentials;

import org.chat.backend.services.Model;

public class CredentialsModel implements Model<CredentialsView> {

    public final String usertag;

    public final String username;

    public CredentialsModel(String usertag, String username) {
        this.usertag = usertag;
        this.username = username;
    }

    @Override
    public CredentialsView toView() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toView'");
    }

}
