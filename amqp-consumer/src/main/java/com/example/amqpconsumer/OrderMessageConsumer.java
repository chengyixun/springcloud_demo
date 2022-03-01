package com.example.amqpconsumer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: OrderMessageConsumer
 * @Author: amy
 * @Description: OrderMessageConsumer 订单消息接收处理服务
 * @Date: 2022/3/1
 * @Version: 1.0
 */
@Slf4j
@EnableBinding(OrderInputChannelProcessor.class)
public class OrderMessageConsumer {

	/**
	 * 保存订单逻辑
	 * 1.通过 OrderInputChannelProcessor.SAVE_ORDER_INPUT 接收消息
	 * 2.然后通过 sendTo 将处理后的消息发送到  xxx
	 *
	 * @param message
	 */
	@StreamListener(OrderInputChannelProcessor.SAVE_ORDER_INPUT)
	public void saveOrderMessage(Message<String> message) {
		log.info("保存订单的消息:{}", message);
	}

}
