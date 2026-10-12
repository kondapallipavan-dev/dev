package com.kondapallipavan.dev.api.dto;

import java.time.Instant;

public record ItemResponse(String id, String name, String description, Instant createdAt) {
}
