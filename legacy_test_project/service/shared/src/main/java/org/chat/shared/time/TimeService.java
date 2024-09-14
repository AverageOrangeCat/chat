package org.chat.shared.time;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

@Service
public class TimeService {

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    public static LocalDateTime nowUtc() {
        return LocalDateTime.now(ZoneOffset.UTC);
    }

}
