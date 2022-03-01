package com.example.amqpproducer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: OrderController
 * @Author: amy
 * @Description: OrderController
 * @Date: 2022/3/1
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderMessageProducer orderMessageProducer;

	@GetMapping("/send")
	public void sendSaveOrderMessage(@RequestParam("message") String message) {
		orderMessageProducer.sendOrderMessage(message);
		log.info("发送保存订单消息成功");
	}

}
