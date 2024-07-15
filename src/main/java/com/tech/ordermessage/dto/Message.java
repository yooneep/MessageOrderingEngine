package com.tech.ordermessage.dto;

public class Message {
    private long timestamp;
    private String content;

    public Message(long timestamp, String content) {
        this.timestamp = timestamp;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "timestamp=" + timestamp +
                ", content='" + content + '\'' +
                '}';
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
