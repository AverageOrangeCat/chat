package org.chat.backend.services.credentials;

import org.chat.backend.exceptions.CredentialsNotFoundException;
import org.chat.backend.repositories.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredentialsService {

    @Autowired
    private CredentialsRepository credentialsRepository;

    public CredentialsModel getModel(String usertag) throws CredentialsNotFoundException {
        var credentialsView = credentialsRepository
                .getView(usertag)
                .orElseThrow(() -> new CredentialsNotFoundException());
        return credentialsView.toModel();
    }

    public void create(CredentialsCreateModel credentialsCreateModel) {
        credentialsRepository.create(credentialsCreateModel.toView());
    }

    public void update(CredentialsUpdateModel credentialsUpdateModel) {
        var credentialsUpdateView = credentialsUpdateModel.toView();
        credentialsUpdateView.optionalUsertag.map((u) -> credentialsRepository.updateUsertag(u));
        credentialsUpdateView.optionalUsername.map((u) -> credentialsRepository.updateUsername(u));
        credentialsUpdateView.optionalPassword.map((p) -> credentialsRepository.updatePassword(p));
    }

    public void delete() {
        credentialsRepository.delete();
    }

}
