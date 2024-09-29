package org.chat.backend.services.credentials;

import java.util.Optional;

public class CredentialsUpdateModel {

    private Optional<String> optionalUsertag = Optional.empty();

    private Optional<String> optionalUsername = Optional.empty();

    private Optional<String> optionalPassword = Optional.empty();

    public CredentialsUpdateModel setOptionalUsertag(
            Optional<String> optionalUsertag) {
        this.optionalUsertag = optionalUsertag;
        return this;
    }

    public Optional<String> getOptionalUsertag() {
        return optionalUsertag;
    }

    public CredentialsUpdateModel setOptionalUsername(Optional<String> optionalUsername) {
        this.optionalUsername = optionalUsername;
        return this;
    }

    public Optional<String> getOptionalUsername() {
        return optionalUsername;
    }

    public CredentialsUpdateModel setOptionalPassword(Optional<String> optionalPassword) {
        this.optionalPassword = optionalPassword;
        return this;
    }

    public Optional<String> getOptionalPassword() {
        return optionalPassword;
    }

}
