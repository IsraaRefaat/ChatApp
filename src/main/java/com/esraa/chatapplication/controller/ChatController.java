package com.esraa.chatapplication.controller;


import com.esraa.chatapplication.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {


    @MessageMapping("chat.sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message message) {
        return message;
    }

    public Message addUser
            (@Payload Message message , SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("user", message.getSender());
        return message;
    }

}
