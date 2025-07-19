package com.algo.demo.service.publishservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.algo.demo.wrapper.consumers.Message;

@Component
public class Publisher implements IPublisher {

    private final IPublishService publishService;
    
    @Autowired
    public Publisher(@Qualifier("kafkaService") IPublishService publishService) {
        this.publishService = publishService;
    }

    public void publish(Message message) {
        this.publishService.publishMessage(message);
    }

    @Override
    public void publishErrorMessage(Message message) {

    }

    @Override
    public void publishInitMessage() {
        Message firstMessage = new Message();
        firstMessage.setMessage("first packet");
        this.publishService.publishMessage(firstMessage);
    }

    @Override
    public void publishEndMessage() {
        Message lastMessage = new Message();
        lastMessage.setIsLastPacket();
        this.publishService.publishMessage(lastMessage);
    }

}
