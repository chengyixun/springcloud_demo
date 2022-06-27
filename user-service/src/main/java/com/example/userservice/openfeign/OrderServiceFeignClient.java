package com.example.userservice.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "order-service")
public interface OrderServiceFeignClient {

	// 只要调用query方法，就能够调用order-service中的OrderController#query
	@RequestMapping("/order/query")
	String query();
}
