package com.kondapallipavan.dev.exception;

import com.kondapallipavan.dev.api.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ItemNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFound(ItemNotFoundException ex, HttpServletRequest request) {
		return build(HttpStatus.NOT_FOUND, ex.getMessage(), request, List.of());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
		List<String> details = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getField() + ": " + error.getDefaultMessage())
				.toList();
		return build(HttpStatus.BAD_REQUEST, "Validation failed", request, details);
	}

	private ResponseEntity<ErrorResponse> build(
			HttpStatus status,
			String message,
			HttpServletRequest request,
			List<String> details
	) {
		ErrorResponse body = new ErrorResponse(
				Instant.now(),
				status.value(),
				status.getReasonPhrase(),
				message,
				request.getRequestURI(),
				details
		);
		return ResponseEntity.status(status).body(body);
	}
}
