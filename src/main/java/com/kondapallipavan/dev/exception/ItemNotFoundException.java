package com.kondapallipavan.dev.exception;

public class ItemNotFoundException extends RuntimeException {

	public ItemNotFoundException(String id) {
		super("Item not found: " + id);
	}
}
