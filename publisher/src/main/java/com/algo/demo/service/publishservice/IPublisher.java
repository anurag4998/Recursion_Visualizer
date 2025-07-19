package com.algo.demo.service.publishservice;

import com.algo.demo.wrapper.consumers.Message;

public interface IPublisher {
    void publish(Message message);
    void publishErrorMessage(Message message);
    void publishInitMessage();
    void publishEndMessage();
}
