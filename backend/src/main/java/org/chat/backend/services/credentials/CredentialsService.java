package org.chat.backend.services.credentials;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.chat.backend.exceptions.CredentialsNotFoundException;
import org.chat.backend.repositories.CredentialsRepository;
import org.chat.backend.utils.crypto.CryptoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredentialsService {

    @Autowired
    private CredentialsRepository credentialsRepository;

    public CredentialsModel getCredentialsModel(String usertag) throws CredentialsNotFoundException {
        var credentialsView = credentialsRepository
                .getCredentialsView(usertag)
                .orElseThrow(() -> new CredentialsNotFoundException());

        return new CredentialsModel()
                .setUsertag(credentialsView.getUsertag())
                .setUsername(credentialsView.getUsername());
    }

    public void create(CredentialsCreateModel credentialsCreateModel)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {

        var passwordSalt = CryptoUtils.generateSecureRandomBytes(16);
        var passwordHash = CryptoUtils.generateSha256Hash(passwordSalt + credentialsCreateModel.getPassword());
        var credentialsCreateView = new CredentialsCreateView()
                .setUsertag(credentialsCreateModel.getUsertag())
                .setUsername(credentialsCreateModel.getUsername())
                .setPasswordSalt(passwordSalt)
                .setPasswordHash(passwordHash);

        credentialsRepository.create(credentialsCreateView);
    }

    public void update(CredentialsUpdateModel credentialsUpdateModel)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {

        if (credentialsUpdateModel
                .getOptionalUsertag()
                .isPresent()) {

            credentialsRepository.updateUsertag(
                    credentialsUpdateModel
                            .getOptionalUsertag()
                            .get());
        }

        if (credentialsUpdateModel
                .getOptionalUsername()
                .isPresent()) {

            credentialsRepository.updateUsertag(
                    credentialsUpdateModel
                            .getOptionalUsername()
                            .get());
        }

        if (credentialsUpdateModel
                .getOptionalPassword()
                .isPresent()) {

            var passwordSalt = CryptoUtils.generateSecureRandomBytes(16);
            var passwordHash = CryptoUtils.generateSha256Hash(passwordSalt +
                    credentialsUpdateModel
                            .getOptionalPassword()
                            .get());

            credentialsRepository.updatePasswordSalt(passwordSalt);
            credentialsRepository.updatePasswordHash(passwordHash);
        }
    }

    public void delete() {
        credentialsRepository.delete();
    }

}
