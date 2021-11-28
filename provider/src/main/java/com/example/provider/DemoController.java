package com.example.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: DemoController
 * @Author: amy
 * @Description: DemoController
 * @Date: 2021/7/13
 * @Version: 1.0
 */
@RestController
@RequestMapping("/demo")
@RefreshScope
public class DemoController {

	@Value("${service.version}")
	private String version;

	@GetMapping("/getVersion")
	public String getVersion() {
		return version;
	}
}
