package com.atguigu.springcloud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

@EnableBinding(Sink.class)
@Slf4j
public class IMessageConsumer {

    @StreamListener(Sink.INPUT)
    public void consumeMessage(Message<String> message) {
        log.info(message.getPayload());
    }
}
