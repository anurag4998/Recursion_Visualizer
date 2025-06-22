package com.algo.demo.pubSub;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algo.demo.controller.Message;

@Component
public class Publisher implements ISubject {

    private final List<IObservable> observers = new ArrayList<>();

    @Autowired
    public Publisher(IObservable observer) {
        this.observers.add(observer);
    }

    @Override
    public boolean register(IObservable obj) {
        // Logic to register an observable object
        System.out.println("Registering observable: " + obj);
        return true;
    }

    @Override
    public boolean remove(IObservable obj) {
        // Logic to remove an observable object
        System.out.println("Removing observable: " + obj);
        return true;
    }

    @Override
    public void notifyObservers(Message data) {
        // Logic to notify all registered observers with the provided data
        System.out.println("Notifying observers with data: " + data);
        this.observers.forEach(observer -> {
            observer.update(data);
        });
    }

}
