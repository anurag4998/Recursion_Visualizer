package com.algo.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algo.demo.pubSub.IObservable;
import com.algo.demo.service.Service;

@Component
public class Listener implements IObservable{
    @Autowired
    private Service service;
    @Override
    public void update(Message data) {
        System.out.println("Received update: " + data);
        service.publish("my-topic", data);
    }

    
}