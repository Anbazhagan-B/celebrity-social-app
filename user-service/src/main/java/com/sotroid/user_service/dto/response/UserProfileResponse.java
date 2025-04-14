package com.sotroid.user_service.dto.response;

import com.sotroid.user_service.entity.User;

public class UserProfileResponse {

    public UserProfileResponse(User user) {
        setId(user.getId());
        setEmail(user.getEmail());
        setName(user.getName());
        setPhoneNumber(user.getPhoneNumber());
        setProfilePictureUrl(user.getProfilePictureUrl());
    }
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String profilePictureUrl;

    public void setId(Long id) { this.id = id; }
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getProfilePictureUrl() { return profilePictureUrl; }
    public void setProfilePictureUrl(String profilePictureUrl) { this.profilePictureUrl = profilePictureUrl; }

}
