package org.chat.backend.services.session;

public class SessionView {

    private Long id = 0L;

    private String bearToken = "";

    private String usertag = "";

    public SessionView setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getId() {
        return id;
    }

    public SessionView setBearToken(String bearToken) {
        this.bearToken = bearToken;
        return this;
    }

    public String getBearToken() {
        return bearToken;
    }

    public SessionView setUsertag(String usertag) {
        this.usertag = usertag;
        return this;
    }

    public String getUsertag() {
        return usertag;
    }

}
