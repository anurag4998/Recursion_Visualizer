package com.algo.demo.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algo.demo.pubsub.Publisher;
import com.algo.demo.wrapper.RecursiveTwo;
import com.algo.demo.wrapper.RecursiveWrapper;
import com.algo.demo.wrapper.consumers.Message;
import com.algo.demo.wrapper.RecursiveWrapperImpl;

@Component
public class Code {
    
    @Autowired
    private Publisher publisher;

    int weightToCarryInDay(int[] weights, int weight) {
        int days = 1;
        int right = 0;
        int sum = 0;
        while(right < weights.length) {
            sum += weights[right];
            if(sum > weight) {
                days++;
                sum = weights[right];
            }
            right++;
        }
        
        return days;
    }


   
    public void main(int n, RecursiveTwo funcToExecute) {
        Message firstMessage = new Message();
        firstMessage.setMessage("first packet");
        publisher.publish(firstMessage);
        RecursiveWrapper<Integer,int[], Integer> wrapper =  RecursiveWrapperImpl.wrapperTwo(funcToExecute, (Message msg) -> {
            System.out.println("Hi");
            this.publisher.publish(msg);
        },
        "fibonacci at "
        );
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);

        wrapper.call(n, dp, null, wrapper);
        Message lastMessage = new Message();
        lastMessage.setIsLastPacket();
        publisher.publish(lastMessage);
    }

   
}
