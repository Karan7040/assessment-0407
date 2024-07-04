package com.example.springjmsexam.producer;

import jakarta.jms.Topic;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsProducer {
    private JmsTemplate jmsTemplate;
    private Topic topic;

    public JmsProducer(JmsTemplate jmsTemplate,Topic topic) {
        this.jmsTemplate = jmsTemplate;
        this.topic=topic;
    }
    public void produce() {
        jmsTemplate.convertAndSend(topic,"Passed with distinction");
    }
}
