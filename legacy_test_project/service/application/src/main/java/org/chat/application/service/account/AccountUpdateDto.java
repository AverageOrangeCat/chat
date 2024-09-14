package org.chat.application.service.account;

public class AccountUpdateDto {

    private String usertag = "";
    private String username = "";
    private String password = "";

    public AccountUpdateDto setUsertag(String usertag) {
        this.usertag = usertag;
        return this;
    }

    public String getUsertag() {
        return usertag;
    }

    public AccountUpdateDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AccountUpdateDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPassword() {
        return password;
    }

}
