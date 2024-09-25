package org.chat.backend.controllers;

import org.chat.backend.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class DefaultController {

    @RequestMapping("/**")
    public void getAccountData(HttpServletRequest request) throws NotFoundException {
        throw new NotFoundException(request.getRequestURI());
    }

}
