
package com.algo.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algo.demo.pubSub.Publisher;
import com.algo.demo.templates.RecursiveFour;

@Component
public class RecursionRunner {

    @Autowired
    private Publisher publisher;

    public <T1, T2, T3,T4, T5, R> R runRecursiveFunctionWithParentId(RecursiveFiveWithParentId<T1, T2, T3, T4, T5, R> function, T1 param1, T2 param2, T3 param3, T4 param4, T5 param5) {
        Message firstMessage = new Message();
        firstMessage.setMessage("first packet");
        publisher.notifyObservers(firstMessage);
        R result =  function.recurse(function, param1, param2, param3, param4, param5, 0, null, null);
        Message lastMessage = new Message();
        lastMessage.setIsLastPacket();
        publisher.notifyObservers(lastMessage);
        return result;
    }

    public <T1, T2, R> R runRecursiveTwoWithParentId(RecursiveTwoWithParentID<T1, T2, R> function, T1 param1, T2 param2) {
        Message firstMessage = new Message();
        firstMessage.setMessage("first packet");
        publisher.notifyObservers(firstMessage);
        R result = function.recurse(function, param1, param2, 0, null, null);
        Message lastMessage = new Message();
        lastMessage.setIsLastPacket();
        publisher.notifyObservers(lastMessage);
        return result;
    }

    public <T1, T2, T3, R> R runRecursiveThreeWithParentId(RecursiveThreeWithParentID<T1, T2, T3, R> function, T1 param1, T2 param2, T3 param3) {
        Message firstMessage = new Message();
        firstMessage.setMessage("first packet");
        publisher.notifyObservers(firstMessage);
        R result = function.recurse(function, param1, param2, param3 , 0, null, null);
        Message lastMessage = new Message();
        lastMessage.setIsLastPacket();
        publisher.notifyObservers(lastMessage);
        return result;
    }

    public <T1, T2, T3, T4, R> R runRecursiveFourWithParentId(RecursiveFour<T1, T2, T3, T4, R> function, T1 param1, T2 param2, T3 param3, T4 param4) {
        Message firstMessage = new Message();
        firstMessage.setMessage("first packet");
        publisher.notifyObservers(firstMessage);
        R result = function.recurse(function, param1, param2, param3 , param4);
        Message lastMessage = new Message();
        lastMessage.setIsLastPacket();
        publisher.notifyObservers(lastMessage);
        return result;
    }

}