package com.example.springjmsexam.producer;

import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.MessageProducer;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsQueueProducer {
    public void produce() throws JMSException {
        // todo : I see duplicate code in QueueConsumer and QueueProducer. Can we refactor it?
        String brokerURL = "tcp://localhost:61616";
        String queueName = "EXAM_QUEUE";

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession();

        Destination destination = session.createQueue(queueName);

        MessageProducer messageProducer = session.createProducer(destination);

        String message = "Examination failed";

        TextMessage textMessage = session.createTextMessage(message);

        messageProducer.send(textMessage);

        session.close();
        connection.close();
    }
}
