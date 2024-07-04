package com.example.springjmsexam;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsProducer {

    private final JmsTemplate jmsTemplate;

    public JmsProducer(JmsTemplate jmsTemplate)
    {
        this.jmsTemplate = jmsTemplate;
    }


    public void produce()
    {
        jmsTemplate.convertAndSend("TEST_QUEUE","This is a spring jms message");
    }
}
