package com.sotroid.chat_service.service;

import com.sotroid.chat_service.dto.RequestDTOs.ChatMessage;
import com.sotroid.chat_service.entity.Message;
import com.sotroid.chat_service.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired private SimpMessagingTemplate messagingTemplate;
    @Autowired private KafkaTemplate<String, Object> kafkaTemplate;

    public Message processIncomingMessage(ChatMessage dto) {
        Message message = new Message();
        message.setFromUserId(dto.getFromUserId());
        message.setToUserId(dto.getToUserId());
        message.setContent(dto.getContent());
        message.setTimestamp(LocalDateTime.now());
        message.setStatus("SENT");
        Message savedMessage = messageRepository.save(message);

        //kafkaTemplate.send("chat-topic", dto);
        messagingTemplate.convertAndSend("/topic/messages/" + dto.getToUserId(), dto);

        return savedMessage;
    }

    public List<Message> getChat(Long userA, Long userB)
    {
        return messageRepository.findByFromUserIdAndToUserIdOrToUserIdAndFromUserId(userA, userB, userA, userB);
    }
}
