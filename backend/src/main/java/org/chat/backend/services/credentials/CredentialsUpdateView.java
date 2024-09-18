package org.chat.backend.services.credentials;

import java.util.Optional;

import org.chat.backend.services.View;

public class CredentialsUpdateView implements View<CredentialsUpdateModel> {

    public final Optional<String> optionalUsertag;

    public final Optional<String> optionalUsername;

    public final Optional<String> optionalPassword;

    public CredentialsUpdateView(
            Optional<String> optionalUsertag,
            Optional<String> optionalUsername,
            Optional<String> optionalPassword) {
        this.optionalUsertag = optionalUsertag;
        this.optionalUsername = optionalUsername;
        this.optionalPassword = optionalPassword;
    }

    @Override
    public CredentialsUpdateModel toModel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toModel'");
    }

}
