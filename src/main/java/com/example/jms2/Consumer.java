package com.example.jms2;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    JmsTemplate jmsTemplate;

    @JmsListener(destination = "sample.queue")
    public void receiveMessage() {

    }


}
