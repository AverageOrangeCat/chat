package org.chat.application.service.account;

public class AccountLoginDto {
    
    private String usertag = "";
    private String password = "";

    public AccountLoginDto setUsertag(String usertag) {
        this.usertag = usertag;
        return this;
    }

    public String getUsertag() {
        return usertag;
    }

    public AccountLoginDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPassword() {
        return password;
    }

}
