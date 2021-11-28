package com.example.provider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: EchoController
 * @Author: amy
 * @Description: EchoController
 * @Date: 2021/7/13  服务端代码
 * @Version: 1.0
 */
@RestController
@RequestMapping
public class EchoController {

	/**
	 * 可以部署多个实例 ，feign会自动完成 类似robbin的轮训
	 * 
	 * @param string
	 * @return
	 */
	@GetMapping(value = "/echo/{string}")
	public String echo(@PathVariable String string) {
		return string;
	}
}
