package org.example.jmstest.config;

import jakarta.jms.Topic;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
public class JmsConfig {
// todo : can we put mq properties in application.yaml to fetch dynamically?
    @Bean
    public ActiveMQConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL("tcp://localhost:61616");
        factory.setUserName("admin");
        factory.setPassword("admin");
        return  factory;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        return new JmsTemplate(this.connectionFactory());
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(this.connectionFactory());
        return factory;
    }
// todo: here i see configuration of topic(publisher-subscriber) but the producer and consumer implementation
//  is of type point-to-point. what exactly you want to do?
//  solution : create separate package of point-point & publisher-subscriber and implement the same.
    @Bean
    public Topic topic(){
        return new ActiveMQTopic("TEST_TOPIC");
    }
}
