package com.algo.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.algo.demo.controller.Message;
import com.algo.demo.service.kafkaproducer.MessageProducer;

@org.springframework.stereotype.Service
public class Service {

    private MessageProducer messageProducer;
    @Autowired 
    public Service( MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
        // Constructor logic if needed
    }
    public void publish(String topic, Message message) {
        // Logic to publish a message to the specified topic
        System.out.println("Publishing message: " + message + " to topic: " + topic);
        messageProducer.sendMessage(topic, message);
    }
}
