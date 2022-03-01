package com.example.amqpproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(value = {OrderOutputChannelProcessor.class})
public class AmqpProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmqpProducerApplication.class, args);
	}

}
