package com.sotroid.interaction_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_relationships", uniqueConstraints = @UniqueConstraint(columnNames = {"fromUserId", "toUserId"}))
public class UserRelationship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fromUserId;
    private Long toUserId;

    @Enumerated(EnumType.STRING)
    private RelationshipType type; // CONNECTED or AVOIDED

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getFromUserId() {
        return fromUserId;
    }
    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }
    public Long getToUserId() {
        return toUserId;
    }
    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }
    public RelationshipType getType() {
        return type;
    }
    public void setType(RelationshipType type) {
        this.type = type;
    }

}
