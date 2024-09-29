package org.chat.backend.services.session;

public class SessionLoginModel {

    private String usertag = "";

    private String password = "";

    public SessionLoginModel setUsertag(String usertag) {
        this.usertag = usertag;
        return this;
    }

    public String getUsertag() {
        return usertag;
    }

    public SessionLoginModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPassword() {
        return password;
    }

}
