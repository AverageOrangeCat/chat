package org.chat.backend.services.user;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

@Service
@RequestScope
public class UserService {
    private User user = new User();

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
