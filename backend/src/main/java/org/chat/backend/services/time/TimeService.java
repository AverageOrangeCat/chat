package org.chat.backend.services.time;

import java.time.ZonedDateTime;

import org.springframework.stereotype.Service;

@Service
public class TimeService {
    
    public static ZonedDateTime now() {
        return ZonedDateTime.now();
    }

}
