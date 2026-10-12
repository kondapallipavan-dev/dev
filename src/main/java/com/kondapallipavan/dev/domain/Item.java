package com.kondapallipavan.dev.domain;

import java.time.Instant;

public record Item(String id, String name, String description, Instant createdAt) {
}
