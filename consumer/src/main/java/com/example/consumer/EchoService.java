package com.example.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: EchoService
 * @Author: amy
 * @Description: EchoService
 * @Date: 2021/7/13   服务调用，客户端代码
 * @Version: 1.0
 */
@FeignClient(value = "provider")
public interface EchoService {

	@RequestMapping(value = "/echo/{str}")
	String echo(@PathVariable("str") String str);

}
