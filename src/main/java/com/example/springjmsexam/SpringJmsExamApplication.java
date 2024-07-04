package com.example.springjmsexam;

import com.example.springjmsexam.consumer.JmsConsumer;
import com.example.springjmsexam.consumer.JmsQueueConsumer;
import com.example.springjmsexam.producer.JmsProducer;
import com.example.springjmsexam.producer.JmsQueueProducer;
import jakarta.jms.JMSException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringJmsExamApplication implements CommandLineRunner {
// todo: can we use here Constructor Injection instead of Field Injection?
    @Autowired
    private JmsProducer jmsProducer;
    @Autowired
    private JmsConsumer jmsConsumer;

    public static void main(String[] args) {
        // todo : Overall : 7/10
        SpringApplication.run(SpringJmsExamApplication.class, args);

    }
    @Override
    public void run(String... args) throws JMSException {
        jmsProducer.produce();
        jmsConsumer.receiveMessage("Passed with distinction");
        JmsQueueProducer jmsQueueProducer=new JmsQueueProducer();
        jmsQueueProducer.produce();
        JmsQueueConsumer jmsQueueConsumer=new JmsQueueConsumer();
        jmsQueueConsumer.consume();

    }

}
