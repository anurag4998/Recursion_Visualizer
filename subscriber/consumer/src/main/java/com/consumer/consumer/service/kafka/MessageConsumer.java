package com.consumer.consumer.service.kafka;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.consumer.consumer.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MessageConsumer {


    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public MessageConsumer(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    @KafkaListener(topics = "my-topic", groupId = "my-group-id")
    public void listen(String data) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Message message = mapper.readValue(data, Message.class); // manual mapping
            System.out.println("Converted: " + message);
            messagingTemplate.convertAndSend("/topic/updates", message);

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}

/**
 * The method readValue(JsonParser, Class<T>) in the type ObjectMapper is not applicable for the arguments (Object, Class<Message>
 */