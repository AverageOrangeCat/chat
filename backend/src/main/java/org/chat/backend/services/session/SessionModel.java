package org.chat.backend.services.session;

import org.chat.backend.services.Model;

public class SessionModel implements Model<SessionView> {

    public final String bearToken;

    public final String usertag;

    public SessionModel(String bearToken, String usertag) {
        this.bearToken = bearToken;
        this.usertag = usertag;
    }

    @Override
    public SessionView toView() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toView'");
    }

}
