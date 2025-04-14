package com.sotroid.interaction_service.dto.response;

public class UserConnectionResponse {
    private Long userId;
    private String status;
    private String name;
    public Long getUserId() {
        return userId;
    }
    public String getName()
    {
        return name;
    }

    public UserConnectionResponse(Long userId, String name, String status) {
        this.userId = userId;
        this.name = name;
        this.status = status;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
