package org.chat.backend.services.session;

import org.chat.backend.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public void login(SessionModel sessionModel) {
        sessionRepository.login(sessionModel.toView());
    }

    public void logout() {
        sessionRepository.logout();
    }

}
