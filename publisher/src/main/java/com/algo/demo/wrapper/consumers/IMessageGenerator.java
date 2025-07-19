package com.algo.demo.wrapper.consumers;

public interface IMessageGenerator {
    String generateString(String template, Object... args);
}
