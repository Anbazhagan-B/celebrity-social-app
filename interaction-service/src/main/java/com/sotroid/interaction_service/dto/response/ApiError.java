package com.sotroid.interaction_service.dto.response;

public class ApiError {
    private String message;
    private String error;

    public ApiError(String message, String error) {
        this.message = message;
        this.error = error;
    }
    public String getMessage() {
        return message;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
