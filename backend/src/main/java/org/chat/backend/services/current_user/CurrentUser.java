package org.chat.backend.services.current_user;

import org.chat.backend.services.credentials.CredentialsView;
import org.chat.backend.services.session.SessionView;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class CurrentUser {

    private SessionView sessionView = new SessionView();

    private CredentialsView credentialsView = new CredentialsView();

    public CurrentUser setSessionView(SessionView sessionView) {
        this.sessionView = sessionView;
        return this;
    }

    public SessionView getSessionView() {
        return sessionView;
    }

    public CurrentUser setCredentialsView(CredentialsView credentialsView) {
        this.credentialsView = credentialsView;
        return this;
    }

    public CredentialsView getCredentialsView() {
        return credentialsView;
    }

}
