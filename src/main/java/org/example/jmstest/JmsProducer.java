package org.example.jmstest;

import jakarta.jms.Topic;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsProducer {
    // todo: if added lombok dependency then why dont you use it? use RequiredArgsConstructor to inject beans
    private final JmsTemplate jmsTemplate;

    private final Topic topic;

    public JmsProducer(JmsTemplate jmsTemplate, Topic topic) {
        this.jmsTemplate = jmsTemplate;
        this.topic = topic;
    }
// todo: you need to call the method to publish the message
    public void produce(){
        jmsTemplate.convertAndSend("JMS_TEST","THIS MESSAGE IS FROM DINESH");
    }

}
