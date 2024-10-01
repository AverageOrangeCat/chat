package org.chat.backend.controllers;

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
@RequestMapping("/credentials")
public class CredentialsController {

    @Autowired
    private CredentialsService credentialsService;

    @GetMapping("/{usertag}")
    public CredentialsModel getCredentialsModel(@PathVariable("usertag") String usertag) throws Exception {
        return credentialsService.getCredentialsModel(usertag);
    }

    @PostMapping("/create")
    public void create(@RequestBody CredentialsCreateModel credentialsCreateModel) throws Exception {
        credentialsService.create(credentialsCreateModel);
    }

    @PutMapping("/update")
    public void update(@RequestBody CredentialsUpdateModel credentialsUpdateModel) throws Exception {
        credentialsService.update(credentialsUpdateModel);
    }

    @DeleteMapping("/delete")
    public void delete() throws Exception {
        credentialsService.delete();
    }

}
