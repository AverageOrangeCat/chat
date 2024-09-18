package org.chat.backend.services.session;

import org.chat.backend.services.View;

public class SessionView implements View<SessionModel> {

    public final Long id;

    public final String bearToken;

    public final String usertag;

    public SessionView(Long id, String bearToken, String usertag) {
        this.id = id;
        this.bearToken = bearToken;
        this.usertag = usertag;
    }

    @Override
    public SessionModel toModel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toModel'");
    }

}
