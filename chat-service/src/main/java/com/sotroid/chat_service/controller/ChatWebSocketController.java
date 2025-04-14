package com.sotroid.chat_service.controller;

import com.sotroid.chat_service.dto.RequestDTOs.ChatMessage;
import com.sotroid.chat_service.entity.Message;
import com.sotroid.chat_service.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatWebSocketController {
    @Autowired
    private final MessageService messageService;
    @Autowired
    private final SimpMessagingTemplate messagingTemplate;

    public ChatWebSocketController(MessageService chatService, SimpMessagingTemplate messagingTemplate) {
        this.messageService = chatService;
        this.messagingTemplate = messagingTemplate;
    }


    @MessageMapping("/chat.send")
    public void handleChat(ChatMessage chatMessage) {
        Message saved = messageService.processIncomingMessage(chatMessage);

        messagingTemplate.convertAndSend(
                "/topic/messages/" + chatMessage.getToUserId(),
                saved
        );
    }
}
