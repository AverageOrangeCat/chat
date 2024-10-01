package org.chat.backend.services.session;

import org.chat.backend.exceptions.InvalidLoginAttemptException;
import org.chat.backend.exceptions.session.SessionNotFoundException;
import org.chat.backend.repositories.CredentialsRepository;
import org.chat.backend.repositories.SessionRepository;
import org.chat.backend.utils.crypto.CryptoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private CredentialsRepository credentialsRepository;

    public SessionModel login(SessionLoginModel sessionLoginModel) throws Exception {

        var passwordView = credentialsRepository
                .getPasswordView(sessionLoginModel.getUsertag())
                .orElseThrow(() -> new InvalidLoginAttemptException());

        var passwordHash = CryptoUtils
                .generateSha256Hash(passwordView.getPasswordSalt() + sessionLoginModel.getPassword());

        if (!passwordView
                .getPasswordHash()
                .equals(passwordHash)) {
            throw new InvalidLoginAttemptException();
        }

        var bearTokenSalt = CryptoUtils.generateSecureRandomBytes(16);
        var bearToken = CryptoUtils.generateSha256Hash(bearTokenSalt + sessionLoginModel.getPassword());
        var sessionLoginView = new SessionLoginView()
                .setUsertag(sessionLoginModel.getUsertag())
                .setBearToken(bearToken);

        var sessionView = sessionRepository
                .login(sessionLoginView)
                .orElseThrow(() -> new SessionNotFoundException(bearToken));

        return new SessionModel()
                .setBearToken(sessionView.getBearToken())
                .setUsertag(sessionView.getUsertag());
    }

    public void logout() throws Exception {
        sessionRepository.logout();
    }

}
