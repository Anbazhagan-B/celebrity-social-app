package com.sotroid.media_service.controller;

import com.sotroid.media_service.dto.UploadResponse;
import com.sotroid.media_service.service.S3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/media")
public class MediaController {
    private final S3Service s3Service;

    public MediaController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public ResponseEntity<UploadResponse> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "folder", defaultValue = "common") String folder
    ) {
        String url = s3Service.uploadFile(file, folder);
        return ResponseEntity.ok(new UploadResponse(url));
    }
}
