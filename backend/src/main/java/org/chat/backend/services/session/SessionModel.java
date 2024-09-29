package org.chat.backend.services.session;

public class SessionModel {

    private String bearToken = "";

    private String usertag = "";

    public SessionModel setBearToken(String bearToken) {
        this.bearToken = bearToken;
        return this;
    }

    public String getBearToken() {
        return bearToken;
    }

    public SessionModel setUsertag(String usertag) {
        this.usertag = usertag;
        return this;
    }

    public String getUsertag() {
        return usertag;
    }

}
