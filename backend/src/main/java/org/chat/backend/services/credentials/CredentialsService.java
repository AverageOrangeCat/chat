package org.chat.backend.services.credentials;

import org.chat.backend.exceptions.credentials.CredentialsNotFoundException;
import org.chat.backend.repositories.CredentialsRepository;
import org.chat.backend.utils.crypto.CryptoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredentialsService {

    @Autowired
    private CredentialsRepository credentialsRepository;

    public CredentialsModel getCredentialsModel(String usertag) throws Exception {
        var credentialsView = credentialsRepository
                .getCredentialsView(usertag)
                .orElseThrow(() -> new CredentialsNotFoundException(usertag));

        return new CredentialsModel()
                .setUsertag(credentialsView.getUsertag())
                .setUsername(credentialsView.getUsername());
    }

    public void create(CredentialsCreateModel credentialsCreateModel) throws Exception {
        var passwordSalt = CryptoUtils.generateSecureRandomBytes(16);
        var passwordHash = CryptoUtils.generateSha256Hash(passwordSalt + credentialsCreateModel.getPassword());
        var credentialsCreateView = new CredentialsCreateView()
                .setUsertag(credentialsCreateModel.getUsertag())
                .setUsername(credentialsCreateModel.getUsername())
                .setPasswordSalt(passwordSalt)
                .setPasswordHash(passwordHash);

        credentialsRepository.createCredentials(credentialsCreateView);
    }

    public void update(CredentialsUpdateModel credentialsUpdateModel) throws Exception {
        if (credentialsUpdateModel
                .getOptionalUsertag()
                .isPresent()) {

            credentialsRepository.updateCredentialUsertag(
                    credentialsUpdateModel
                            .getOptionalUsertag()
                            .get());
        }

        if (credentialsUpdateModel
                .getOptionalUsername()
                .isPresent()) {

            credentialsRepository.updateCredentialUsername(
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

            credentialsRepository.updateCredentialPasswordSalt(passwordSalt);
            credentialsRepository.updateCredentialPasswordHash(passwordHash);
        }
    }

    public void delete() throws Exception {
        credentialsRepository.deleteCredentials();
    }

}
