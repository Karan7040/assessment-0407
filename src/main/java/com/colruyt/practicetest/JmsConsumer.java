package com.colruyt.practicetest;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component

public class JmsConsumer {

    @JmsListener(destination = "CONTENT",containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(String message) {

        System.out.println("message: " + message);
    }

}
