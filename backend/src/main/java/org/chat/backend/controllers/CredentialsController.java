package org.chat.backend.controllers;

import java.util.Optional;

import org.chat.backend.services.credentials.CredentialsCreateModel;
import org.chat.backend.services.credentials.CredentialsModel;
import org.chat.backend.services.credentials.CredentialsService;
import org.chat.backend.services.credentials.CredentialsUpdateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/credentials")
public class CredentialsController {

    @Autowired
    private CredentialsService credentialsService;

    @GetMapping("/{usertag}")
    public Optional<CredentialsModel> getModel(@PathVariable String usertag) {
        return credentialsService.getModel(usertag);
    }

    @PostMapping("/create")
    public void create(@RequestBody CredentialsCreateModel credentialsCreateModel) {
        credentialsService.create(credentialsCreateModel);
    }

    @PutMapping("/update")
    public void update(@RequestBody CredentialsUpdateModel credentialsUpdateModel) {
        credentialsService.update(credentialsUpdateModel);
    }

    @DeleteMapping("/delete")
    public void delete() {
        credentialsService.delete();
    }

}
