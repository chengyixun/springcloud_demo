package com.example.amqp;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: DefaultMessageListener
 * @Author: amy
 * @Description: DefaultMessageListener service 服务类，用来订阅消息，并对消息进行处理。
 * @Date: 2022/2/25
 * @Version: 1.0
 */
@Slf4j
@Component
public class DefaultMessageListener {

	@StreamListener(Processor.INPUT)
	public void processMyMessage(String message) {
		log.info("接收到消息:{}", message);
	}
}
