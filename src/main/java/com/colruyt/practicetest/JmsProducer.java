package com.colruyt.practicetest;

import jakarta.jms.Topic;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component

public class JmsProducer {
    private final JmsTemplate jmsTemplate;
    private final Topic topic;

    public JmsProducer(JmsTemplate jmsTemplate,Topic topic) {
        this.jmsTemplate = jmsTemplate;
        this.topic=topic;
    }

    public void produce(){
        jmsTemplate.convertAndSend(topic,"SPRING JMS");
    }
}
