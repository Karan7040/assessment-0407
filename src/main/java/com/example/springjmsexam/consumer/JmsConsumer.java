package com.example.springjmsexam.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsConsumer {
    @JmsListener(destination = "EXAM_TOPIC", containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }
}
