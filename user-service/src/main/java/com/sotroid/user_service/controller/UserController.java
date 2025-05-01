package com.sotroid.user_service.controller;


import com.sotroid.user_service.dto.request.LoginRequest;
import com.sotroid.user_service.dto.response.UserProfileResponse;
import com.sotroid.user_service.dto.request.UserRegisterRequest;
import com.sotroid.user_service.service.MediaClientService;
import com.sotroid.user_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    final UserService userService;
    final MediaClientService mediaClientService;

    public UserController(UserService userService,  MediaClientService mediaClientService) {
        this.userService = userService;
        this.mediaClientService = mediaClientService;
    }

    @PostMapping("/register")
    public UserProfileResponse registerUser(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("password") String password,
            @RequestParam("profileImage") MultipartFile profileImage
    ) {
        String imageUrl = mediaClientService.uploadToMediaService(profileImage);
        UserRegisterRequest request = new UserRegisterRequest();
        request.setName(name);
        request.setEmail(email);
        request.setProfilePictureUrl(imageUrl);
        request.setPassword(password);
        request.setPhoneNumber(phoneNumber);
        return userService.registerUser(request);
    }

    @GetMapping("/{userId}")
    public UserProfileResponse getProfile(@PathVariable Long userId) {
        return userService.getUserProfile(userId);
    }

    @GetMapping()
    public UserProfileResponse getusers() {
        return userService.getUserProfile(1L);
    }

//    @PostMapping("/validateByEmail")
//    public ResponseEntity<UserProfileResponse> login(@RequestBody LoginRequest loginRequest)
//    {
//        return ResponseEntity.ok(userService.validateLogin(loginRequest));
//    }
}
