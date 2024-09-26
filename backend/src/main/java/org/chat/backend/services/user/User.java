package org.chat.backend.services.user;

import org.chat.backend.services.credentials.CredentialsView;
import org.chat.backend.services.session.SessionView;
import org.springframework.stereotype.Component;

@Component
public class User {

    private SessionView sessionView = new SessionView();

    private CredentialsView credentialsView = new CredentialsView();

    public User setSessionView(SessionView sessionView) {
        this.sessionView = sessionView;
        return this;
    }

    public SessionView getSessionView() {
        return sessionView;
    }

    public User setCredentialsView(CredentialsView credentialsView) {
        this.credentialsView = credentialsView;
        return this;
    }

    public CredentialsView getCredentialsView() {
        return credentialsView;
    }

}
