package com.kondapallipavan.dev.service;

import com.kondapallipavan.dev.api.dto.HelloResponse;
import java.time.Instant;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

	public HelloResponse greet(String name) {
		String visitor = (name == null || name.isBlank()) ? "world" : name.trim();
		return new HelloResponse("Hello, " + visitor + "!", Instant.now());
	}
}
