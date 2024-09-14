package org.chat.api.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.chat.shared.time.TimeService;

public class CookieBuilder {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd-MMM-yyyy HH:mm:ss Z");

    private String name = "";
    private String value = "";
    private LocalDateTime expirationDate = TimeService.nowUtc();

    public CookieBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public CookieBuilder setValue(String value) {
        this.value = value;
        return this;
    }

    public String getValue() {
        return value;
    }

    public CookieBuilder setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public String build() {
        return String.format("%s=%s; Expires=%s; Path=/", name, value, expirationDate.atZone(ZoneOffset.UTC).format(formatter));
    }

}
