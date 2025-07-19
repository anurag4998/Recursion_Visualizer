package com.algo.demo.wrapper.consumers;

import java.text.MessageFormat;

public class MessageGenerator implements IMessageGenerator {
    public String generateString(String template, Object ...args) {
        return MessageFormat.format(template, args);
    }
}
