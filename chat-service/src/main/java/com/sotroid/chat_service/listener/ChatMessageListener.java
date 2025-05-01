package com.sotroid.chat_service.listener;

import com.sotroid.chat_service.dto.RequestDTOs.ChatMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class ChatMessageListener {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatMessageListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @KafkaListener(topics = "chat-topic", groupId = "chat-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(ChatMessage message) {
        System.out.println("Kafka RECEIVED: " + message.getContent());
        messagingTemplate.convertAndSend("/topic/messages/" + message.getToUserId(), message);
    }
}
