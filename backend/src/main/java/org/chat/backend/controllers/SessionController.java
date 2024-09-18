package org.chat.backend.controllers;

import org.chat.backend.services.session.SessionModel;
import org.chat.backend.services.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/session")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping("/login")
    public void login(@RequestBody SessionModel sessionModel) {
        sessionService.login(sessionModel);
    }

    @DeleteMapping("/logout")
    public void logout() {
        sessionService.logout();
    }

}
