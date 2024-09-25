package org.chat.backend.interceptors;

import java.util.Optional;

import org.chat.backend.exceptions.BearTokenNotProvidedException;
import org.chat.backend.exceptions.CredentialsNotFoundException;
import org.chat.backend.exceptions.SessionNotFoundException;
import org.chat.backend.repositories.CredentialsRepository;
import org.chat.backend.repositories.SessionRepository;
import org.chat.backend.services.user.User;
import org.chat.backend.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class VerificationInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws BearTokenNotProvidedException, SessionNotFoundException, CredentialsNotFoundException {
        var bearToken = Optional
                .ofNullable(request.getHeader("Authorization"))
                .orElseThrow(() -> new BearTokenNotProvidedException())
                .replace("Bearer ", "");

        var sessionView = sessionRepository
                .getView(bearToken)
                .orElseThrow(() -> new SessionNotFoundException(bearToken));

        var credentialsView = credentialsRepository
                .getView(sessionView.usertag)
                .orElseThrow(() -> new CredentialsNotFoundException(sessionView.usertag));

        var user = new User()
                .setSessionView(sessionView)
                .setCredentialsView(credentialsView);

        userService.setUser(user);
        return true;
    }

}
