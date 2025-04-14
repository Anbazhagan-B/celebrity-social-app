package com.sotroid.interaction_service.service;

import com.sotroid.interaction_service.dto.request.RelationshipRequest;
import com.sotroid.interaction_service.dto.response.UserResponse;
import com.sotroid.interaction_service.dto.response.UserConnectionResponse;
import com.sotroid.interaction_service.entity.RelationshipType;
import com.sotroid.interaction_service.entity.UserRelationship;
import com.sotroid.interaction_service.exception.ConnectionNotFoundException;
import com.sotroid.interaction_service.repository.UserRelationshipRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRelationshipService {
    @Value("${user.service.url}")
    private String UserServiceUrl;
    private final UserRelationshipRepository relationshipRepository;
    private final RestTemplate restTemplate;
    private final RedisPrefixService redisPrefixService;

    public UserRelationshipService(UserRelationshipRepository userRelationshipRepository, RestTemplate restTemplate, RedisPrefixService redisPrefixService) {
        this.relationshipRepository = userRelationshipRepository;
        this.restTemplate = restTemplate;
        this.redisPrefixService = redisPrefixService;
    }

    public void connect(RelationshipRequest request) {
        validateUsers(request);
        saveOrUpdateRelationship(request, RelationshipType.CONNECTED);
    }

    public void avoid(RelationshipRequest request) {
        validateUsers(request);
        saveOrUpdateRelationship(request, RelationshipType.AVOIDED);
    }

    public List<UserConnectionResponse> getConnections(Long userId) {
        if (!relationshipRepository.existsById(userId)) {
            throw new ConnectionNotFoundException("Connection with UserID " + userId + " not found");
        }

        List<UserRelationship> connections = relationshipRepository
                .findByFromUserIdAndType(userId, RelationshipType.CONNECTED);

        return connections.stream()
                .map(rel -> {
                    UserResponse user = restTemplate.getForObject(UserServiceUrl + "/api/users/" + rel.getToUserId(), UserResponse.class);
                    redisPrefixService.addUserNameToPrefixes(user.getName());
                    return new UserConnectionResponse(
                            rel.getToUserId(),
                            user.getName(),
                            rel.getType().name()
                    );
                })
                .collect(Collectors.toList());
    }

    private void saveOrUpdateRelationship(RelationshipRequest request, RelationshipType type) {
        UserRelationship relationship = new UserRelationship();
        relationship.setFromUserId(request.getFromUserId());
        relationship.setToUserId(request.getToUserId());
        relationship.setType(type);
        relationshipRepository.save(relationship);
    }

    private void validateUsers(RelationshipRequest request) {
//        if (request.getFromUserId().equals(request.getToUserId())) {
//            throw new IllegalArgumentException("You cannot connect/avoid yourself");
//        }

//        if (!relationshipRepository.existsById(request.getFromUserId())) {
//            throw new ConnectionNotFoundException("FromUser with ID " + request.getFromUserId() + " not found");
//        }
//
//        if (!relationshipRepository.existsById(request.getToUserId())) {
//            throw new ConnectionNotFoundException("ToUser with ID " + request.getToUserId() + " not found");
//        }
    }
}
