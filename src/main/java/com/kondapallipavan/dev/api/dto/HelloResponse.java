package com.kondapallipavan.dev.api.dto;

import java.time.Instant;

public record HelloResponse(String message, Instant timestamp) {
}
