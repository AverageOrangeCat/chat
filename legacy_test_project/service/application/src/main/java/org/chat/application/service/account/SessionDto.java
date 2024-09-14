package org.chat.application.service.account;

import java.time.LocalDateTime;

import org.chat.shared.time.TimeService;

public class SessionDto {

    private String accessToken = "";
    private LocalDateTime expirationDate = TimeService.nowUtc();

    public SessionDto setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public SessionDto setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

}
