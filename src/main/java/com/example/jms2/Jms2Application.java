package com.example.jms2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Jms2Application {

    public static void main(String[] args) {

        SpringApplication.run(Jms2Application.class, args);
        ConfigurableApplicationContext context = SpringApplication.run(Jms2Application.class, args);

        Producer producer = context.getBean(Producer.class);
        producer.sendMessage("sample.queue");
    }

}
