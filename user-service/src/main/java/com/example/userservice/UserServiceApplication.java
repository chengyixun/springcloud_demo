package com.example.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients //表示开启openfeign扫描功能
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}


	// 向IoC容器注入RestTemplate
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}


	@Bean
	@LoadBalanced  // 让一个普通的RestTemplate具备Ribbon的能力
	public RestTemplate restTemplateRibbon(){
		return new RestTemplate();
	}
}
