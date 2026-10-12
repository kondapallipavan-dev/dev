package com.kondapallipavan.dev.api;

import com.kondapallipavan.dev.api.dto.CreateItemRequest;
import com.kondapallipavan.dev.api.dto.ItemResponse;
import com.kondapallipavan.dev.service.ItemService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

	private final ItemService itemService;

	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}

	@GetMapping
	public List<ItemResponse> list() {
		return itemService.findAll();
	}

	@GetMapping("/{id}")
	public ItemResponse get(@PathVariable String id) {
		return itemService.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ItemResponse create(@Valid @RequestBody CreateItemRequest request) {
		return itemService.create(request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String id) {
		itemService.delete(id);
	}
}
