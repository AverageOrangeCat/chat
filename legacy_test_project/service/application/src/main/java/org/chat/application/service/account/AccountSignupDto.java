package org.chat.application.service.account;

public class AccountSignupDto {

    private String usertag = "";
    private String username = "";
    private String password = "";

    public AccountSignupDto setUsertag(String usertag) {
        this.usertag = usertag;
        return this;
    }

    public String getUsertag() {
        return usertag;
    }

    public AccountSignupDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AccountSignupDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPassword() {
        return password;
    }

}
