package com.algo.demo.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.algo.demo.service.Service;
import com.algo.demo.wrapper.consumers.Message;

@Component
public class Publisher {

    private Service service;

    @Value("${spring.kafka.producer.topic}")
    private String topic;
    
    @Autowired
    public Publisher(Service service) {
        this.service = service;
    }

    public void publish(Message message) {
        this.service.publish(topic, message);
    }

}
