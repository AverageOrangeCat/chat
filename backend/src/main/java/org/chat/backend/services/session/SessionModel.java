package org.chat.backend.services.session;

public class SessionModel {

    public final String bearToken;

    public final String usertag;

    public SessionModel(String bearToken, String usertag) {
        this.bearToken = bearToken;
        this.usertag = usertag;
    }

    public SessionView toView() {
        return new SessionView(0L, bearToken, usertag);
    }

}
