package com.algo.demo.pubSub;

import com.algo.demo.controller.Message;

public interface ISubject {
    public boolean register(IObservable obj);
	
	public boolean remove(IObservable obj);
	
	public void notifyObservers(Message data);
}
