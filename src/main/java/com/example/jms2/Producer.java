package com.example.jms2;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    JmsTemplate jmsTemplate;

    public void sendMessage(String destination) {
        jmsTemplate.convertAndSend(destination, "hii");
    }
}
