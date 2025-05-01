package com.sotroid.post_service.controller;

import com.sotroid.post_service.dto.RequestDTOs.PostRequest;
import com.sotroid.post_service.dto.ResponseDTOs.PostResponse;
import com.sotroid.post_service.exception.ImageUploadException;
import com.sotroid.post_service.service.MediaClientService;
import com.sotroid.post_service.service.PostService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final MediaClientService mediaClientService;

    public PostController(PostService postService, MediaClientService mediaClientService) {
        this.postService = postService;
        this.mediaClientService = mediaClientService;
    }

    @PostMapping
    public PostResponse createPost(@RequestParam("image") MultipartFile image, @RequestParam("caption") String caption, @RequestParam("userId") Long userId) {
       try
       {
           PostRequest request = new PostRequest();
           request.setCaption(caption);
           request.setUserId(userId);
           request.setImageUrl(mediaClientService.uploadToMediaService(image));
           return postService.createPost(request);
       }
       catch (Exception e)
       {
           throw new ImageUploadException("Failed to upload image or create post", e);
       }
    }

    @GetMapping("/{userId}")
    public List<PostResponse> getUserPosts(@PathVariable Long userId) {
        return postService.getPostsByUser(userId);
    }

    @GetMapping("/explore/{currentUserId}")
    public List<PostResponse> getAllExceptCurrentUser(@PathVariable Long currentUserId) {
        return postService.getAllPostsExceptCurrentUser(currentUserId);
    }
}
