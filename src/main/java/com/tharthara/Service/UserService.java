package com.tharthara.Service;

import com.tharthara.Entity.User;

public interface UserService {
    User register(User user);

    void setOnline(String username);

    void setOffline(String username);

    User getUser(String username);
}
