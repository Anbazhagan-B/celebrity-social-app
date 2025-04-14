package com.sotroid.post_service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ImageUploadException.class)
    public ResponseEntity<?> handleImageUploadError(ImageUploadException ex) {
        return ResponseEntity.status(502).body(Map.of(
                "error", "Image Upload Failed",
                "message", ex.getMessage()
        ));
    }

    @ExceptionHandler(PostCreationException.class)
    public ResponseEntity<?> handlePostCreationError(PostCreationException ex) {
        return ResponseEntity.status(500).body(Map.of(
                "error", "Post Creation Failed",
                "message", ex.getMessage()
        ));
    }
}
