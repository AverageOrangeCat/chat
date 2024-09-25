package org.chat.backend.services.session;

public class SessionLoginView {

    public final String bearToken;

    public final String usertag;

    public SessionLoginView(String bearToken, String usertag) {
        this.bearToken = bearToken;
        this.usertag = usertag;
    }
    
}
