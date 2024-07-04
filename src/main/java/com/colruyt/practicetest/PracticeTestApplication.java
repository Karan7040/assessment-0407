package com.colruyt.practicetest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PracticeTestApplication implements CommandLineRunner {

	@Autowired
	private JmsProducer jmsProducer;

	public static void main(String[] args) {
		SpringApplication.run(PracticeTestApplication.class, args);
	}
		@Override
		public void run(String... args) throws Exception{
			jmsProducer.produce();
		}

	}



