package com.example.amqplog;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

/**
 * @ClassName: MyProcessor
 * @Author: amy
 * @Description: MyProcessor 自定义消息通道接口
 * todo 有时间再把demo 写完 https://www.cnblogs.com/fengzheng/p/11576661.html
 *
 * @Date: 2022/2/25
 * @Version: 1.0
 */
@Component
public interface MyProcessor {
	String MESSAGE_INPUT = "log_input";

	String MESSAGE_OUTPUT = "log_output";

	String LOG_FORMAT_INPUT = "log_format_input";

	String LOG_FORMAT_OUTPUT = "log_format_output";

	@Input(MESSAGE_INPUT)
	SubscribableChannel logInput();

	@Output(MESSAGE_OUTPUT)
	MessageChannel logOutput();

	@Input(LOG_FORMAT_INPUT)
	SubscribableChannel logFormatInput();

	@Output(LOG_FORMAT_OUTPUT)
	MessageChannel logFormatOutput();

}
