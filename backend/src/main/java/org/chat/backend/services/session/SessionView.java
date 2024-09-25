package org.chat.backend.services.session;

public class SessionView {

    public final Long id;

    public final String bearToken;

    public final String usertag;

    public SessionView(Long id, String bearToken, String usertag) {
        this.id = id;
        this.bearToken = bearToken;
        this.usertag = usertag;
    }

    public SessionModel toModel() {
        return new SessionModel(bearToken, usertag);
    }

}
