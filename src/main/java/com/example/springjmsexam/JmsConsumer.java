package com.example.springjmsexam;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsConsumer {

    @JmsListener(destination = "TEST_QUEUE")
    public void recieveMessage(String message) {
        System.out.println("Received Message: " + message);
    }
}
