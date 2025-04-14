package com.sotroid.chat_service.dto.RequestDTOs;
public class ChatMessage {
    private Long fromUserId;
    private Long toUserId;
    private String content;
    private String timestamp;
    private String status;
    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }
    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Long getFromUserId() {
        return fromUserId;
    }
    public Long getToUserId() {
        return toUserId;
    }
    public String getContent() {
        return content;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public String getStatus() {
        return status;
    }
}
