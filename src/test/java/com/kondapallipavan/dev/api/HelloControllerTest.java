package com.kondapallipavan.dev.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void helloDefaultsToWorld() throws Exception {
		mockMvc.perform(get("/api/v1/hello"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value("Hello, world!"))
				.andExpect(jsonPath("$.timestamp").exists());
	}

	@Test
	void helloUsesProvidedName() throws Exception {
		mockMvc.perform(get("/api/v1/hello").param("name", "Pavan"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value("Hello, Pavan!"));
	}
}
