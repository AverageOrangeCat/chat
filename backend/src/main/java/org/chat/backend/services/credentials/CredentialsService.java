package org.chat.backend.services.credentials;

import java.util.Optional;

import org.chat.backend.repositories.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredentialsService {

    @Autowired
    private CredentialsRepository credentialsRepository;

    public Optional<CredentialsModel> getModel(String usertag) {
        Optional<CredentialsView> optionalCredentialsView = credentialsRepository.getView(usertag);
        return optionalCredentialsView.map((c) -> c.toModel());
    }

    public void create(CredentialsCreateModel credentialsCreateModel) {
        credentialsRepository.create(credentialsCreateModel.toView());
    }

    public void update(CredentialsUpdateModel credentialsUpdateModel) {
        CredentialsUpdateView credentialsUpdateView = credentialsUpdateModel.toView();
        credentialsUpdateView.optionalUsertag.map((u) -> credentialsRepository.updateUsertag(u));
        credentialsUpdateView.optionalUsername.map((u) -> credentialsRepository.updateUsername(u));
        credentialsUpdateView.optionalPassword.map((p) -> credentialsRepository.updatePassword(p));
    }

    public void delete() {
        credentialsRepository.delete();
    }

}
