package org.chat.backend.services.credentials;

import java.util.Optional;

public class CredentialsUpdateView {

    private Optional<String> optionalUsertag = Optional.empty();

    private Optional<String> optionalUsername = Optional.empty();

    private Optional<String> optionalPassword = Optional.empty();

    public CredentialsUpdateView setOptionalUsertag(
            Optional<String> optionalUsertag) {
        this.optionalUsertag = optionalUsertag;
        return this;
    }

    public Optional<String> getOptionalUsertag() {
        return optionalUsertag;
    }

    public CredentialsUpdateView setOptionalUsername(
            Optional<String> optionalUsername) {
        this.optionalUsername = optionalUsername;
        return this;
    }

    public Optional<String> getOptionalUsername() {
        return optionalUsername;
    }

    public CredentialsUpdateView setOptionalPassword(
            Optional<String> optionalPassword) {
        this.optionalPassword = optionalPassword;
        return this;
    }

    public Optional<String> getOptionalPassword() {
        return optionalPassword;
    }

    public CredentialsUpdateModel toModel() {
        return new CredentialsUpdateModel()
                .setOptionalUsertag(optionalUsertag)
                .setOptionalUsername(optionalUsername)
                .setOptionalPassword(optionalPassword);
    }

}
