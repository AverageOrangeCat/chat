package org.chat.backend.services.session;

import org.apache.commons.codec.digest.DigestUtils;
import org.chat.backend.exceptions.InvalidLoginAttemptException;
import org.chat.backend.repositories.CredentialsRepository;
import org.chat.backend.services.time.TimeService;

public class SessionLoginModel {

    private String usertag = "";

    private String password = "";

    public SessionLoginModel setUsertag(String usertag) {
        this.usertag = usertag;
        return this;
    }

    public String getUsertag() {
        return usertag;
    }

    public SessionLoginModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SessionLoginView toView(CredentialsRepository credentialsRepository)
            throws InvalidLoginAttemptException {
        credentialsRepository
                .getView(usertag, password)
                .orElseThrow(() -> new InvalidLoginAttemptException());

        String bearToken = DigestUtils.sha256Hex(usertag + password + TimeService.now());

        return new SessionLoginView()
                .setUsertag(usertag)
                .setBearToken(bearToken);
    }

}
