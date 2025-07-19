package com.algo.demo.service.publishservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.algo.demo.service.publishservice.IPublishService;
import com.algo.demo.wrapper.consumers.Message;

@Component
@Qualifier("kafkaService")
@Primary
public class KafkaService implements IPublishService {

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    @Value("${spring.kafka.producer.topic}")
    private String topic;

    private void sendMessage(String topic, Message message) {
        kafkaTemplate.send(topic, message);
    }

    @Override
    public void publishMessage(Message message) {
        this.sendMessage(topic, message);
    }

}