package org.chat.backend.services.session;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.cxf.rt.security.crypto.CryptoUtils;
import org.chat.backend.exceptions.InvalidLoginAttemptException;
import org.chat.backend.repositories.CredentialsRepository;

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
        var passwordSalt = credentialsRepository
                .getPasswordSalt(usertag)
                .orElseThrow(() -> new InvalidLoginAttemptException());

        var passwordBytes = password.getBytes(StandardCharsets.UTF_8);
        var passwordHash = DigestUtils.sha3_256(
                ByteBuffer.allocate(passwordSalt.length + passwordBytes.length)
                        .put(passwordSalt)
                        .put(passwordBytes)
                        .array());

        credentialsRepository
                .getCredentialsView(usertag, passwordSalt, passwordHash)
                .orElseThrow(() -> new InvalidLoginAttemptException());

        var bearTokenSalt = CryptoUtils.generateSecureRandomBytes(16);
        var bearToken = DigestUtils.sha3_256Hex(
                ByteBuffer.allocate(bearTokenSalt.length + passwordHash.length)
                        .put(bearTokenSalt)
                        .put(passwordHash)
                        .array());

        return new SessionLoginView()
                .setUsertag(usertag)
                .setBearToken(bearToken);
    }

}
