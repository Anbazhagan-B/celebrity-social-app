package com.sotroid.post_service.dto.ResponseDTOs;

import com.sotroid.post_service.entity.Post;

import java.time.LocalDateTime;

public class PostResponse {
    private Long id;
    private Long userId;
    private String imageUrl;
    private String caption;
    private LocalDateTime createdAt;
    private String userName;

    public PostResponse() {}

    public PostResponse(Long id, Long userId, String imageUrl, String caption, LocalDateTime createdAt, String userName) {
        this.id = id;
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.caption = caption;
        this.createdAt = createdAt;
        this.userName = userName;
    }

    public PostResponse(Post post)
    {
        this.id = post.getId();
        this.userId = post.getUserId();
        this.imageUrl = post.getImageUrl();
        this.caption = post.getCaption();
        this.createdAt = post.getCreatedAt();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getCaption() {
        return caption;
    }
    public void setCaption(String caption) {
        this.caption = caption;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
