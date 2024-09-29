package org.chat.backend.controllers;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.chat.backend.exceptions.InvalidLoginAttemptException;
import org.chat.backend.exceptions.SessionNotFoundException;
import org.chat.backend.services.session.SessionLoginModel;
import org.chat.backend.services.session.SessionModel;
import org.chat.backend.services.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping("/login")
    public void login(@RequestBody SessionLoginModel sessionLoginModel, HttpServletResponse response)
            throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidLoginAttemptException,
            SessionNotFoundException {

        var sessionModel = sessionService.login(sessionLoginModel);
        var sessionCookie = new Cookie("Authorization", sessionModel.getBearToken());
        response.addCookie(sessionCookie);
    }

    @DeleteMapping("/logout")
    public void logout(HttpServletResponse response) throws SessionNotFoundException {
        sessionService.logout();
        var sessionCookie = new Cookie("Authorization", "");
        response.addCookie(sessionCookie);
    }

}
