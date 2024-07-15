package com.tech.ordermessage.event;

import com.lmax.disruptor.EventHandler;
import com.tech.ordermessage.dto.Message;

public class MessageEventHandler implements EventHandler<MessageEvent> {

    // 滑动窗口大小（以时间戳为单位）
    private static final long WINDOW_SIZE = 100;

    private long currentTimestamp;

    @Override
    public void onEvent(MessageEvent event, long sequence, boolean endOfBatch) throws Exception{
        Message message = event.getMessage();
        if (message.getTimestamp() >= currentTimestamp && message.getTimestamp() < currentTimestamp + WINDOW_SIZE) {
            System.out.println(message);
            currentTimestamp = message.getTimestamp();
        }
    }
}
