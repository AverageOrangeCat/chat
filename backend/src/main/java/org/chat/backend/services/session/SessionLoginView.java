package org.chat.backend.services.session;

public class SessionLoginView {

    private String bearToken = "";

    private String usertag = "";

    public SessionLoginView setBearToken(String bearToken) {
        this.bearToken = bearToken;
        return this;
    }

    public String getBearToken() {
        return bearToken;
    }

    public SessionLoginView setUsertag(String usertag) {
        this.usertag = usertag;
        return this;
    }

    public String getUsertag() {
        return usertag;
    }

}
