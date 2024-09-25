package org.chat.backend.services.user;

import org.chat.backend.services.credentials.CredentialsView;
import org.chat.backend.services.session.SessionView;
import org.springframework.stereotype.Component;

@Component
public class User {

    public SessionView sessionView = new SessionView(0L, "", "");

    public CredentialsView credentialsView = new CredentialsView(0L, "", "", "");

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
