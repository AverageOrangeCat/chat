package org.chat.backend.interceptors;

import java.util.Optional;

import org.chat.backend.exceptions.BearTokenNotProvidedException;
import org.chat.backend.exceptions.credentials.CredentialsNotFoundException;
import org.chat.backend.exceptions.session.SessionNotFoundException;
import org.chat.backend.repositories.CredentialsRepository;
import org.chat.backend.repositories.SessionRepository;
import org.chat.backend.services.current_user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class VerificationInterceptor implements HandlerInterceptor {

    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        var bearToken = Optional
                .ofNullable(request.getHeader("Authorization"))
                .orElseThrow(() -> new BearTokenNotProvidedException())
                .replace("Bearer ", "");

        var sessionView = sessionRepository
                .getSessionView(bearToken)
                .orElseThrow(() -> new SessionNotFoundException(bearToken));

        var credentialsView = credentialsRepository
                .getCredentialsView(sessionView.getUsertag())
                .orElseThrow(() -> new CredentialsNotFoundException(sessionView.getUsertag()));

        currentUser
                .setSessionView(sessionView)
                .setCredentialsView(credentialsView);

        return true;
    }

}
