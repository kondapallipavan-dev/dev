package com.kondapallipavan.dev.api;

import com.kondapallipavan.dev.api.dto.HelloResponse;
import com.kondapallipavan.dev.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HelloController {

	private final HelloService helloService;

	public HelloController(HelloService helloService) {
		this.helloService = helloService;
	}

	@GetMapping("/hello")
	public HelloResponse hello(@RequestParam(required = false) String name) {
		return helloService.greet(name);
	}
}
