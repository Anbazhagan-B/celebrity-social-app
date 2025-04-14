package com.sotroid.interaction_service.dto.response;

public class UserResponse {
    private Long id;
    private String name;
    private String profileImageUrl;

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
    public String getProfileImageUrl() {
        return profileImageUrl;
    }
}
