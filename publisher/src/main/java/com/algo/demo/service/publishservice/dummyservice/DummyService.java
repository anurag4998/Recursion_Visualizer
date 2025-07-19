package com.algo.demo.service.publishservice.dummyservice;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.algo.demo.service.publishservice.IPublishService;
import com.algo.demo.wrapper.consumers.Message;

@Component
@Qualifier("dummyService")
public class DummyService implements IPublishService {

    @Override
    public void publishMessage(Message message) {
        throw new UnsupportedOperationException("Unimplemented method 'publishMessage'");
    }

}
