package com.kondapallipavan.dev.service;

import com.kondapallipavan.dev.api.dto.CreateItemRequest;
import com.kondapallipavan.dev.api.dto.ItemResponse;
import com.kondapallipavan.dev.domain.Item;
import com.kondapallipavan.dev.exception.ItemNotFoundException;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

	private final Map<String, Item> items = new ConcurrentHashMap<>();

	public List<ItemResponse> findAll() {
		return items.values().stream()
				.map(this::toResponse)
				.toList();
	}

	public ItemResponse findById(String id) {
		Item item = items.get(id);
		if (item == null) {
			throw new ItemNotFoundException(id);
		}
		return toResponse(item);
	}

	public ItemResponse create(CreateItemRequest request) {
		Item item = new Item(
				UUID.randomUUID().toString(),
				request.name().trim(),
				request.description() == null ? "" : request.description().trim(),
				Instant.now()
		);
		items.put(item.id(), item);
		return toResponse(item);
	}

	public void delete(String id) {
		if (items.remove(id) == null) {
			throw new ItemNotFoundException(id);
		}
	}

	private ItemResponse toResponse(Item item) {
		return new ItemResponse(item.id(), item.name(), item.description(), item.createdAt());
	}
}
