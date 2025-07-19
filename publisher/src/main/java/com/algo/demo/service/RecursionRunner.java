package com.algo.demo.service;

import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algo.demo.wrapper.consumers.Message;
import com.algo.demo.wrapper.funcItfs.RecursiveFour;
import com.algo.demo.wrapper.funcItfs.RecursiveFunc;
import com.algo.demo.wrapper.funcItfs.RecursiveThree;
import com.algo.demo.wrapper.funcItfs.RecursiveTwo;
import com.algo.demo.service.publishservice.IPublisher;
import com.algo.demo.wrapper.RecursiveWrapperImpl;

@Component
public class RecursionRunner {
    
    private final IPublisher publisher;
    private final ExecutorService executorService;
    
    @Autowired
    public RecursionRunner(IPublisher publisher, ExecutorService executorService) {
        this.publisher = publisher;
        this.executorService = executorService;
    }

    public void runRecursiveComputation(RecursiveFunc funcToExecute, String templateMessage, Object... args) {
        executorService.submit(() -> {
            publishFirstMessage();
            invokeRecursiveWrapper(funcToExecute, templateMessage, args);
            publishLastMessage();
        });
    }

    private void publishFirstMessage() {
        Message firstMessage = new Message();
        firstMessage.setMessage("first packet");
        publisher.publish(firstMessage);
    }

    private void publishLastMessage() {
        Message lastMessage = new Message();
        lastMessage.setIsLastPacket();
        publisher.publish(lastMessage);
    }

    @SuppressWarnings("unchecked")
    private void invokeRecursiveWrapper(RecursiveFunc funcToExecute, String templateMessage, Object... args) {
        if (funcToExecute instanceof RecursiveTwo && args.length == 2) {
            var wrapper = RecursiveWrapperImpl.wrapperTwo(
                (RecursiveTwo<Object, Object, Object>) funcToExecute,
                publisher::publish,
                templateMessage
            );
            wrapper.call(args[0], args[1], null, wrapper);

        } else if (funcToExecute instanceof RecursiveThree && args.length == 3) {
            var wrapper = RecursiveWrapperImpl.wrapperThree(
                (RecursiveThree<Object, Object, Object, Object>) funcToExecute,
                publisher::publish,
                "Hello"
            );
            wrapper.call(args[0], args[1], args[2], null, wrapper);

        } else if (funcToExecute instanceof RecursiveFour && args.length == 4) {
            var wrapper = RecursiveWrapperImpl.wrapperFour(
                (RecursiveFour<Object, Object, Object, Object, Object>) funcToExecute,
                publisher::publish,
                "Hello"
            );
            wrapper.call(args[0], args[1], args[2], args[3], null, wrapper);

        } else {
            throw new IllegalArgumentException("Unsupported function type or incorrect number of arguments.");
        }
    }



   
}

//TODO:

/**
 * 
 * 1)Handle cases where Kafka is not working. messages are not produced.
 * 2)Handle cases where a null pointer exeception happens or some other logical exception happens in the user program
 * 3)Handle use cases where a message is failed to be delivered.
 * 4)When the producer goes down.
 * 5)Don't block the main thread
 */