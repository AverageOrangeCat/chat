package org.chat.backend.interceptors;

import java.util.Optional;

import org.chat.backend.exceptions.BearTokenNotProvidedException;
import org.chat.backend.exceptions.CredentialsNotFoundException;
import org.chat.backend.exceptions.SessionNotFoundException;
import org.chat.backend.repositories.CredentialsRepository;
import org.chat.backend.repositories.SessionRepository;
import org.chat.backend.services.credentials.CredentialsView;
import org.chat.backend.services.session.SessionView;
import org.chat.backend.services.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class VerificationInterceptor implements HandlerInterceptor {

    // Currently logged in user

    @Autowired
    private User user;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws BearTokenNotProvidedException, SessionNotFoundException, CredentialsNotFoundException {

        String bearToken = Optional
                .ofNullable(request.getHeader("bear_token"))
                .orElseThrow(() -> new BearTokenNotProvidedException());

        SessionView sessionView = sessionRepository
                .getView(bearToken)
                .orElseThrow(() -> new SessionNotFoundException(bearToken));

        CredentialsView credentialsView = credentialsRepository
                .getView(sessionView.usertag)
                .orElseThrow(() -> new CredentialsNotFoundException(sessionView.usertag));

        user = new User(sessionView, credentialsView);
        return true;
    }

}
