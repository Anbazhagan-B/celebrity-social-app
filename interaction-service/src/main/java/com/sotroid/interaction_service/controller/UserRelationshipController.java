package com.sotroid.interaction_service.controller;

import com.sotroid.interaction_service.dto.request.RelationshipRequest;
import com.sotroid.interaction_service.dto.response.UserConnectionResponse;
import com.sotroid.interaction_service.service.RedisPrefixService;
import com.sotroid.interaction_service.service.UserRelationshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relationships")
public class UserRelationshipController {

    private final UserRelationshipService userRelationshipService;
    private final RedisPrefixService redisPrefixService;

    public UserRelationshipController(UserRelationshipService userRelationshipService, RedisPrefixService redisPrefixService) {
        this.userRelationshipService = userRelationshipService;
        this.redisPrefixService = redisPrefixService;
    }

    @PostMapping("/connect")
    public ResponseEntity<String> connect(@RequestBody RelationshipRequest request) {
        userRelationshipService.connect(request);
        return ResponseEntity.ok("Connected successfully");
    }

    @PostMapping("/avoid")
    public ResponseEntity<String> avoid(@RequestBody RelationshipRequest request) {
        userRelationshipService.avoid(request);
        return ResponseEntity.ok("Rejected successfully");
    }

    @GetMapping("/connections/{userId}")
    public List<UserConnectionResponse> getConnections(@PathVariable Long userId) {
        redisPrefixService.clearAllPrefixKeys();
        return userRelationshipService.getConnections(userId);
    }

    @GetMapping("/search")
    public ResponseEntity<List<String>> searchByPrefix(@RequestParam("prefix") String prefix) {
        List<String> results = redisPrefixService.getSuggestions(prefix, 3);
        return ResponseEntity.ok(results);
    }
}