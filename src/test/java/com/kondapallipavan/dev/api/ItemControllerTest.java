package com.kondapallipavan.dev.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void createGetAndDeleteItem() throws Exception {
		MvcResult created = mockMvc.perform(post("/api/v1/items")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"Notebook\",\"description\":\"Dev notes\"}"))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.name").value("Notebook"))
				.andExpect(jsonPath("$.description").value("Dev notes"))
				.andReturn();

		JsonNode body = objectMapper.readTree(created.getResponse().getContentAsString());
		String id = body.get("id").asText();

		mockMvc.perform(get("/api/v1/items/" + id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(id))
				.andExpect(jsonPath("$.name").value("Notebook"));

		mockMvc.perform(delete("/api/v1/items/" + id))
				.andExpect(status().isNoContent());

		mockMvc.perform(get("/api/v1/items/" + id))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.status").value(404));
	}

	@Test
	void createRejectsBlankName() throws Exception {
		mockMvc.perform(post("/api/v1/items")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"\"}"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.error").value("Bad Request"));
	}
}
