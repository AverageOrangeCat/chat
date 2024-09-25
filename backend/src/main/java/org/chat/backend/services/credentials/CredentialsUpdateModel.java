package org.chat.backend.services.credentials;

import java.util.Optional;

public class CredentialsUpdateModel {

    public final Optional<String> optionalUsertag;

    public final Optional<String> optionalUsername;

    public final Optional<String> optionalPassword;

    // Implemented for jacksons deserialization actions

    public CredentialsUpdateModel() {
        this.optionalUsertag = Optional.empty();
        this.optionalUsername = Optional.empty();
        this.optionalPassword = Optional.empty();
    }

    public CredentialsUpdateModel(
            Optional<String> optionalUsertag,
            Optional<String> optionalUsername,
            Optional<String> optionalPassword) {
        this.optionalUsertag = optionalUsertag;
        this.optionalUsername = optionalUsername;
        this.optionalPassword = optionalPassword;
    }

    public CredentialsUpdateView toView() {
        return new CredentialsUpdateView(
                optionalUsertag,
                optionalUsername,
                optionalPassword);
    }

}
