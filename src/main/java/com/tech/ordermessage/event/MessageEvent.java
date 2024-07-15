package com.tech.ordermessage.event;

import com.tech.ordermessage.dto.Message;

public class MessageEvent {
    private Message message;

    public void setMessage(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }
}
