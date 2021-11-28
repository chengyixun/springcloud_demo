package com.example.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: TestController
 * @Author: amy
 * @Description: TestController
 * @Date: 2021/7/13
 * @Version: 1.0
 */
@RestController
@RequestMapping
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EchoService echoService;


    @GetMapping("/echo-rest/{str}")
    public String rest(@PathVariable String str){
        return restTemplate.getForObject("http://provider/echo/"+str,String.class);
    }

    @GetMapping("/echo-feign/{str}")
    public String feign(@PathVariable String str){
        return echoService.echo(str);
    }



}
