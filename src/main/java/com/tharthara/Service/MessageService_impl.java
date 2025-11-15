package com.tharthara.Service;

import com.tharthara.Entity.Message;
import com.tharthara.Entity.User;
import com.tharthara.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService_impl implements MessageService {

    private MessageRepository messageRepository;

    @Autowired
    public MessageService_impl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message saveMessage(User sender, User receiver, String content) {
        Message newMessage=new Message(sender,receiver,content);
        return messageRepository.save(newMessage);
    }
}
