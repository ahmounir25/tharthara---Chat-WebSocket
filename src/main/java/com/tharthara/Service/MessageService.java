package com.tharthara.Service;

import com.tharthara.Entity.Message;
import com.tharthara.Entity.User;

public interface MessageService {
    public Message saveMessage(User sender, User receiver, String content);
}
