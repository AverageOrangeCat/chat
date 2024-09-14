package org.chat.shared.uuid;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class UuidService {

    public static String uuid() {
        return UUID.randomUUID().toString();
    }

}
