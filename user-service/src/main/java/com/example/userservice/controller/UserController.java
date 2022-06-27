package com.example.userservice.controller;

import com.example.userservice.openfeign.OrderServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/hello")
    public String hello() {
        return "user test";
    }

    // 从Spring IoC容器中获取到DiscoveryClient的实例
    @Autowired
    private DiscoveryClient discoveryClient;

    // 测试服务发现
    @RequestMapping("/getinstances")
    public List<ServiceInstance> getinstances() {
//        "order-service"--->urls
        return this.discoveryClient.getInstances("order-service");
    }

    // http工具类：httpurlconnection、okhttp、httpclient、resttemplate等
    @Resource
    private RestTemplate restTemplate;

    // 获取到对应的uri，就是手写一个随机负载均衡  urls--->url
    @RequestMapping("/manual-loadbalance")
    public String manualLoadbalance() {
        List<ServiceInstance> instances = this.discoveryClient.getInstances("order-service");
        List<String> uris = instances.stream().map(instance -> instance.getUri().toString()).collect(Collectors.toList());
        int index = ThreadLocalRandom.current().nextInt(uris.size());
        String uri = uris.get(index);  // http://192.168.56.1:9091
        String targetUrl = uri + "/order/query";
        // http://192.168.56.1:9092/order/query
        return this.restTemplate.getForObject(targetUrl,String.class);
    }

    @Resource
    private LoadBalancerClient loadBalancerClient;

    // 使用ribbon-api的方式完成负载均衡
    @RequestMapping("/ribbon-api")
    public String ribbonApi() {
        // choose: 服务发现+负载均衡[轮询的]
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("order-service");
        String targetUrl=serviceInstance.getUri().toString()+"order/query";
        return this.restTemplate.getForObject(targetUrl,String.class);
    }

    @Resource
    private RestTemplate restTemplateRibbon;

    // 使用ribbon+restTemplate注解的方式
    @RequestMapping("/ribbon-annotation")
    public String ribbonAnnotation() {
        // http://192.168.56.1:9092/order/query   order-service:应该用了拦截器将其进行拦截，并且order-service进行了服务发现+负载均衡
       return this.restTemplateRibbon.getForObject("http://order-service/order/query",String.class);
    }

    @Resource
    private OrderServiceFeignClient orderServiceFeignClient;


    // RestTemplate不具备面向对象思想的url地址调用的方式
    @RequestMapping("/openfegin")
    public String openfegin() {
        return this.orderServiceFeignClient.query();// 等同于this.restTemplate.getForObject(targetUrl,String.class);
    }
}










