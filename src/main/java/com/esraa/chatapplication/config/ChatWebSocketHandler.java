package com.esraa.chatapplication.config;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Handle the incoming message, no authentication required
        System.out.println("Received message: " + message.getPayload());

        // Send a simple response back to the client
        session.sendMessage(new TextMessage("Message received: " + message.getPayload()));
    }
}