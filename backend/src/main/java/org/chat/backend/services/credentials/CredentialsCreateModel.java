package org.chat.backend.services.credentials;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.cxf.rt.security.crypto.CryptoUtils;

public class CredentialsCreateModel {

    private String usertag = "";

    private String username = "";

    private String password = "";

    public CredentialsCreateModel setUsertag(String usertag) {
        this.usertag = usertag;
        return this;
    }

    public String getUsertag() {
        return usertag;
    }

    public CredentialsCreateModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CredentialsCreateModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CredentialsCreateView toView() {
        var passwordSalt = CryptoUtils.generateSecureRandomBytes(16);
        var passwordBytes = password.getBytes(StandardCharsets.UTF_8);
        var passwordHash = DigestUtils.sha3_256(
                ByteBuffer.allocate(passwordSalt.length + passwordBytes.length)
                        .put(passwordSalt)
                        .put(passwordBytes)
                        .array());

        return new CredentialsCreateView()
                .setUsertag(usertag)
                .setUsername(username)
                .setPasswordSalt(passwordSalt)
                .setPasswordHash(passwordHash);
    }

}
