package org.chat.backend.services.session;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.chat.backend.exceptions.InvalidLoginAttemptException;
import org.chat.backend.repositories.CredentialsRepository;
import org.chat.backend.utils.crypto.CryptoUtils;

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
            throws InvalidLoginAttemptException, NoSuchAlgorithmException, UnsupportedEncodingException {

        var passwordView = credentialsRepository
                .getPasswordView(usertag)
                .orElseThrow(() -> new InvalidLoginAttemptException());

        var passwordHash = CryptoUtils.generateSha256Hash(passwordView.getPasswordSalt() + password);

        if (!passwordView
                .getPasswordHash()
                .equals(passwordHash)) {
            throw new InvalidLoginAttemptException();
        }

        var bearTokenSalt = CryptoUtils.generateSecureRandomBytes(16);
        var bearToken = CryptoUtils.generateSha256Hash(bearTokenSalt + password);

        return new SessionLoginView()
                .setUsertag(usertag)
                .setBearToken(bearToken);
    }

}
