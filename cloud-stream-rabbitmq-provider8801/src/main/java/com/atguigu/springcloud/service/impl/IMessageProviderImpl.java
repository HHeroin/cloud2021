package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(Source.class)
public class IMessageProviderImpl implements IMessageProvider {
    @Autowired
    private MessageChannel output;

    @Override
    public String send() {
        Message<String> message = MessageBuilder.withPayload("hello").build();
        output.send(message);
        return null;
    }
}
