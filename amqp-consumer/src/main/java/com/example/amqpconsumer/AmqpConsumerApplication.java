package com.example.amqpconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(value = { OrderInputChannelProcessor.class })
public class AmqpConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmqpConsumerApplication.class, args);
	}

}
