package org.chat.backend.services.credentials;

import java.util.Optional;

import org.chat.backend.services.Model;

public class CredentialsUpdateModel implements Model<CredentialsUpdateView> {

    public final Optional<String> optionalUsertag;

    public final Optional<String> optionalUsername;

    public final Optional<String> optionalPassword;

    public CredentialsUpdateModel(
            Optional<String> optionalUsertag,
            Optional<String> optionalUsername,
            Optional<String> optionalPassword) {
        this.optionalUsertag = optionalUsertag;
        this.optionalUsername = optionalUsername;
        this.optionalPassword = optionalPassword;
    }

    @Override
    public CredentialsUpdateView toView() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toView'");
    }

}
