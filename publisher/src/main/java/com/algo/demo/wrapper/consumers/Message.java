package com.algo.demo.wrapper.consumers;

import java.util.UUID;

public class Message {
    public int level;
    public String message;
    public UUID parentId;
    public UUID currentId;
    public boolean isLastPacket;

    public Message(int level, String message, UUID parentId ) {
        this.level = level;
        this.message = message;
        this.parentId = parentId;
        this.currentId = UUID.randomUUID();
    }

    public Message() {

    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UUID getParentId() {
        return parentId;
    }
    public void setParentId(UUID parentId) {
        this.parentId = parentId;
    }

    public void setIsLastPacket() {
        this.isLastPacket = true;
    }

    @Override
    public String toString() {
        return this.getMessage() + " at level " + this.getLevel();
    }
}
