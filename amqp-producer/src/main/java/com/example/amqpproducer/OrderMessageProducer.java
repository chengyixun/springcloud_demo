package com.example.amqpproducer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @ClassName: OrderMessageProducer
 * @Author: amy
 * @Description: OrderMessageProducer 订单消息发送者
 * @Date: 2022/3/1
 * @Version: 1.0
 */
@Slf4j
@EnableBinding(value = { OrderOutputChannelProcessor.class })
public class OrderMessageProducer {

	@Autowired
	@Output(OrderOutputChannelProcessor.SAVE_ORDER_OUTPUT)
	private MessageChannel channel;

	public void sendOrderMessage(String message) {
		channel.send(MessageBuilder.withPayload(message).build());
		log.info("消息发送成功:{}", message);
	}

}
