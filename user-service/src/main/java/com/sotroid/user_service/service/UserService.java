package com.sotroid.user_service.service;

import com.sotroid.user_service.dto.request.LoginRequest;
import com.sotroid.user_service.dto.response.UserProfileResponse;
import com.sotroid.user_service.dto.request.UserRegisterRequest;
import com.sotroid.user_service.entity.User;
import com.sotroid.user_service.exception.UserAlreadyExistsException;
import com.sotroid.user_service.repository.UserRepository;
import com.sotroid.user_service.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserProfileResponse registerUser(UserRegisterRequest request) {
        User user = new User();

        Optional<User> existing = userRepository.findByEmail(request.getEmail());
        if (existing.isPresent()) {
            throw new UserAlreadyExistsException("User already exists with email: " + request.getEmail());
        }

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(request.getPassword());
        user.setProfilePictureUrl(request.getProfilePictureUrl());
        User saved = userRepository.save(user);
        return toResponse(saved);
    }

    public UserProfileResponse validateLogin(LoginRequest request)
    {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + request.getEmail()));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new UserNotFoundException("Invalid password for user: " + request.getEmail());
        }
        return toResponse(user);
    }

    public UserProfileResponse getUserProfile(Long userId) {
        return userRepository.findById(userId)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private UserProfileResponse toResponse(User user) {
        return new UserProfileResponse(user);
    }

}
