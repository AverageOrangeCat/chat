package org.chat.backend.services.session;

import org.chat.backend.exceptions.InvalidLoginAttemptException;
import org.chat.backend.exceptions.SessionNotFoundException;
import org.chat.backend.repositories.CredentialsRepository;
import org.chat.backend.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private CredentialsRepository credentialsRepository;

    public SessionModel login(SessionLoginModel sessionLoginModel)
            throws InvalidLoginAttemptException, SessionNotFoundException {
        var sessionLoginView = sessionLoginModel.toView(credentialsRepository);
        var sessionView = sessionRepository
                .login(sessionLoginView)
                .orElseThrow(() -> new SessionNotFoundException());
        return sessionView.toModel();
    }

    public void logout() throws SessionNotFoundException {
        sessionRepository.logout();
    }

}
