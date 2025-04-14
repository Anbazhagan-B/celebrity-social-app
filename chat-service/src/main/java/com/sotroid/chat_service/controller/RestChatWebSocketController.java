package com.sotroid.chat_service.controller;

import com.sotroid.chat_service.dto.RequestDTOs.ChatMessage;
import com.sotroid.chat_service.entity.Message;
import com.sotroid.chat_service.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/chats")
@CrossOrigin(origins = "http://localhost:3000")
public class RestChatWebSocketController {

    @Autowired
    private MessageService messageService;

    @PostMapping()
    public void sendMessage(@RequestBody ChatMessage chatMessage) {
        messageService.processIncomingMessage(chatMessage);
    }

    @GetMapping("/{userA}/{userB}")
    public List<Message> getChatMessages(@PathVariable Long userA, @PathVariable Long userB) {
        return messageService.getChat(userA, userB);
    }
}
