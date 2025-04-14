package com.sotroid.post_service.service;

import com.sotroid.post_service.dto.RequestDTOs.PostRequest;
import com.sotroid.post_service.dto.ResponseDTOs.PostResponse;
import com.sotroid.post_service.dto.ResponseDTOs.UserProfileResponse;
import com.sotroid.post_service.entity.Post;
import com.sotroid.post_service.exception.PostCreationException;
import com.sotroid.post_service.repository.PostRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final RestTemplate restTemplate;

    @Value("${user.service.url}")
    private String userServiceUrl;

    public PostService(PostRepository postRepository, RestTemplate restTemplate) {
        this.postRepository = postRepository;
        this.restTemplate = restTemplate;
    }

    public PostResponse createPost(PostRequest request) {
        try
        {
            UserProfileResponse user = restTemplate.getForObject(userServiceUrl + "/api/users/" + request.getUserId(), UserProfileResponse.class);
            Post post = new Post();
            post.setUserId(request.getUserId());
            post.setImageUrl(request.getImageUrl());
            post.setCaption(request.getCaption());
            post.setCreatedAt(LocalDateTime.now());
            Post saved = postRepository.save(post);
            PostResponse postResponse = new PostResponse(saved);
            postResponse.setUserName(user.getName());
            return postResponse;
        }
        catch (Exception e)
        {
            throw new PostCreationException("Failed to create post in database", e);
        }
    }

    public List<PostResponse> getPostsByUser(Long userId) {

        List<Post> posts = postRepository.findByUserId(userId);

        System.out.println("UserServiceUrl" + userServiceUrl);
        userServiceUrl = userServiceUrl + "/api/users/" + userId;
        UserProfileResponse user = restTemplate.getForObject(userServiceUrl, UserProfileResponse.class);

        return postRepository.findByUserId(userId)
                .stream()
                .map(post -> {

                    return getPostResponse(post, user);
                }).collect(Collectors.toList());
    }

    public List<PostResponse> getAllPostsExceptCurrentUser(Long currentUserId) {
        List<Post> posts = postRepository.findByUserIdNot(currentUserId);

        return posts.stream().map(post -> {
            UserProfileResponse user = restTemplate.getForObject(userServiceUrl + "/api/users/" + post.getId(), UserProfileResponse.class);
            return getPostResponse(post, user);
        }).collect(Collectors.toList());
    }

    private PostResponse getPostResponse(Post post, UserProfileResponse user) {
        PostResponse res = new PostResponse();
        res.setId(post.getId());
        res.setUserId(post.getUserId());
        res.setImageUrl(post.getImageUrl());
        res.setCaption(post.getCaption());
        res.setCreatedAt(post.getCreatedAt());
        res.setUserName(user != null ? user.getName() : null);
        return res;
    }

}