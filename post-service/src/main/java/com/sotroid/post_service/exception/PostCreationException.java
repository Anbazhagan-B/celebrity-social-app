package com.sotroid.post_service.exception;

public class PostCreationException extends RuntimeException {
    public PostCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
