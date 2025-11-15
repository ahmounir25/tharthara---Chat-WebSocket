package com.tharthara.Controller;

import com.tharthara.DTO.ChatMessage;
import com.tharthara.Entity.User;
import com.tharthara.Service.MessageService;
import com.tharthara.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller

public class ChatController {
    private MessageService messageService;
    private UserService userService;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ChatController(MessageService messageService, UserService userService, SimpMessagingTemplate messagingTemplate) {
        this.messageService = messageService;
        this.userService=userService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.send")
    public void sendMessage(ChatMessage message)
    {
        if (message.getReceiver()==null)
        {
            messagingTemplate.convertAndSend("/topic/public",message);
        }else
        { messagingTemplate.convertAndSend("/user/"+message.getReceiver()+"/queue/messages",message);
//        System.out.println("/user/"+message.getReceiver()+"/queue/messages");
        }

        User sender=userService.getUser(message.getSender());
        User receiver=userService.getUser(message.getReceiver());
        messageService.saveMessage(sender,receiver,message.getContent());

    }

}
