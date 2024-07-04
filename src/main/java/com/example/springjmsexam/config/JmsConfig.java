package com.example.springjmsexam.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Configuration
@EnableJms
public class JmsConfig {

 @Bean
    public ActiveMQConnectionFactory connectionFactory()
 {
    ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
     factory.setBrokerURL("tcp://localhost:61616");
     factory.setUserName("demo");
     factory.setPassword("demo");
     return  factory;

 }

    @Bean
    public JmsTemplate jmsTemplate()
    {
        return  new JmsTemplate(connectionFactory());
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        return factory;
    }

}
