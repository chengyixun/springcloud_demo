package com.example.amqpconsumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

/**
 * @ClassName: OrderInputChannelProcessor
 * @Author: amy
 * @Description: OrderInputChannelProcessor 订单消息输入通道处理器
 * @Date: 2022/3/1
 * @Version: 1.0
 */
@Component
public interface OrderInputChannelProcessor {

	/**
	 * 保存订单输入通道（需要与配置文件中的保持一致）
	 */
	String SAVE_ORDER_INPUT = "saveOrderInput";

	@Input(SAVE_ORDER_INPUT)
	SubscribableChannel saveOrderInput();

}
