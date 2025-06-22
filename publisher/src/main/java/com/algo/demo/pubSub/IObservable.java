package com.algo.demo.pubSub;

import com.algo.demo.controller.Message;

public interface IObservable {
    void update(Message data);
}
