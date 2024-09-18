package org.chat.backend.services.user;

import org.chat.backend.services.credentials.CredentialsView;
import org.chat.backend.services.session.SessionView;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class User {

    public final SessionView sessionView;

    public final CredentialsView credentialsView;

    public User(SessionView sessionView, CredentialsView credentialsView) {
        this.sessionView = sessionView;
        this.credentialsView = credentialsView;
    }

}
