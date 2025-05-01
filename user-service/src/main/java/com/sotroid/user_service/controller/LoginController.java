package com.sotroid.user_service.controller;

import com.sotroid.user_service.dto.request.LoginRequest;
import com.sotroid.user_service.dto.response.UserProfileResponse;
import com.sotroid.user_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/validateByEmail")
    public ResponseEntity<UserProfileResponse> login(@RequestBody LoginRequest loginRequest)
    {
        return ResponseEntity.ok(userService.validateLogin(loginRequest));
    }
}
