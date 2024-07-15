package com.tech.ordermessage.service;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.tech.ordermessage.dto.Message;
import com.tech.ordermessage.event.MessageEvent;
import com.tech.ordermessage.event.MessageEventHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DisruptorWithSlidingWindow {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // 缓冲区大小
        int bufferSize = 1024;

        Disruptor<MessageEvent> disruptor = new Disruptor<>(MessageEvent::new, bufferSize, executor, ProducerType.SINGLE, new BlockingWaitStrategy());

        MessageEventHandler messageEventHandler = new MessageEventHandler();
        disruptor.handleEventsWith(messageEventHandler);

        RingBuffer<MessageEvent> ringBuffer = disruptor.start();

        // 模拟生产消息
        long time = 0;
        while (true) {
            long sequence = ringBuffer.next();
            MessageEvent event = ringBuffer.get(sequence);
            event.setMessage(new Message(time++, "Message " + time));
            ringBuffer.publish(sequence);
        }
    }
}
