package org.chat.backend.services.credentials;

import java.util.Optional;

public class CredentialsUpdateView {

    private Optional<String> optionalUsertag = Optional.empty();

    private Optional<String> optionalUsername = Optional.empty();

    private Optional<String> optionalPasswordSalt = Optional.empty();

    private Optional<String> optionalPasswordHash = Optional.empty();

    public CredentialsUpdateView setOptionalUsertag(Optional<String> optionalUsertag) {
        this.optionalUsertag = optionalUsertag;
        return this;
    }

    public Optional<String> getOptionalUsertag() {
        return optionalUsertag;
    }

    public CredentialsUpdateView setOptionalUsername(Optional<String> optionalUsername) {
        this.optionalUsername = optionalUsername;
        return this;
    }

    public Optional<String> getOptionalUsername() {
        return optionalUsername;
    }

    public CredentialsUpdateView setOptionalPasswordSalt(Optional<String> optionalPasswordSalt) {
        this.optionalPasswordSalt = optionalPasswordSalt;
        return this;
    }

    public Optional<String> getOptionalPasswordSalt() {
        return optionalPasswordSalt;
    }

    public CredentialsUpdateView setOptionalPasswordHash(Optional<String> optionalPasswordHash) {
        this.optionalPasswordHash = optionalPasswordHash;
        return this;
    }

    public Optional<String> getOptionalPasswordHash() {
        return optionalPasswordHash;
    }

}
