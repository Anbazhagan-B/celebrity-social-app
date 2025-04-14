package com.sotroid.interaction_service.repository;

import com.sotroid.interaction_service.entity.RelationshipType;
import com.sotroid.interaction_service.entity.UserRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRelationshipRepository extends JpaRepository<UserRelationship, Long> {
    List<UserRelationship> findByFromUserIdAndType(Long fromUserId, RelationshipType type);
    boolean existsById(Long id);
}
