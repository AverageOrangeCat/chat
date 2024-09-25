package org.chat.backend.services.session;

import org.apache.commons.codec.digest.DigestUtils;
import org.chat.backend.exceptions.InvalidLoginAttemptException;
import org.chat.backend.repositories.CredentialsRepository;
import org.chat.backend.services.time.TimeService;

public class SessionLoginModel {

    public final String usertag;

    public final String password;

    // Implemented for jacksons deserialization actions

    public SessionLoginModel() {
        this.usertag = "";
        this.password = "";
    }

    public SessionLoginModel(String usertag, String password) {
        this.usertag = usertag;
        this.password = password;
    }

    public SessionLoginView toView(CredentialsRepository credentialsRepository)
            throws InvalidLoginAttemptException {
        credentialsRepository
                .getView(usertag, password)
                .orElseThrow(() -> new InvalidLoginAttemptException());

        String bearToken = DigestUtils.sha256Hex(usertag + password + TimeService.now());

        return new SessionLoginView(bearToken, usertag);
    }

}
