package com.example.amqpproducer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

/**
 * @ClassName: OrderOutputChannelProcessor
 * @Author: amy
 * @Description: OrderOutputChannelProcessor
 * @Date: 2022/3/1
 * @Version: 1.0
 */
@Component
public interface OrderOutputChannelProcessor {
	/**
	 * 保存订单输出通道（需要与配置文件中的保持一致）
	 */
	String SAVE_ORDER_OUTPUT = "saveOrderOutput";

	@Output(SAVE_ORDER_OUTPUT)
	MessageChannel saveOrderOutput();
}
