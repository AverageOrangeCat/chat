package org.chat.application.service.account;

public class AccountDto {

    private String usertag = "";
    private String username = "";

    public AccountDto setUsertag(String usertag) {
        this.usertag = usertag;
        return this;
    }

    public String getUsertag() {
        return usertag;
    }

    public AccountDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getUsername() {
        return username;
    }
}
