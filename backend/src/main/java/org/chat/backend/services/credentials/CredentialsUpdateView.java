package org.chat.backend.services.credentials;

import java.util.Optional;

public class CredentialsUpdateView {

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

}
