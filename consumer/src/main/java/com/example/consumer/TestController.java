package com.example.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

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
	private EchoServiceFeignClient echoServiceFeignClient;

	@Autowired
	private DiscoveryClient discoveryClient;

	@GetMapping("instances")
	public List<ServiceInstance> getInstances() {
		List<ServiceInstance> instances = discoveryClient.getInstances("provider");
		return instances;
	}

	@GetMapping("manual-balance")
	public String getManulInstances() {
		List<ServiceInstance> instances = discoveryClient.getInstances("provider");
		List<String> urls = instances.stream().map(m -> m.getUri().toString()).collect(Collectors.toList());

		int index = ThreadLocalRandom.current().nextInt(urls.size());
		return urls.get(index);
	}

	@Resource
	private LoadBalancerClient loadBalancerClient;

	@GetMapping("/ribbon-api")
	public String ribbonApi() {
		// choose: 服务发现+负载均衡 [默认 轮询的]
		ServiceInstance serviceInstance = loadBalancerClient.choose("provider");
		String targetUrl = serviceInstance.getUri().toString() + "/demo/getVersion";
		return restTemplate.getForObject(targetUrl, String.class);
	}

	@Autowired
	private RestTemplate restTemplateRibbon;

	@GetMapping("/ribbon-annotation")
	public String ribbonAnnotation() {
		//provider 应该使用了拦截器将其进行拦截，并且 provider实现了 上面"服务发现+负载均衡 [默认 轮询的]"
		return restTemplateRibbon.getForObject("http://provider/demo/getVersion", String.class);
	}

	@GetMapping("/echo-rest/{str}")
	public String rest(@PathVariable String str) {
		return restTemplate.getForObject("http://provider/echo/" + str, String.class);
	}

	@GetMapping("/echo-feign/{str}")
	public String feign(@PathVariable String str) {
		return echoServiceFeignClient.echo(str);
	}

}
