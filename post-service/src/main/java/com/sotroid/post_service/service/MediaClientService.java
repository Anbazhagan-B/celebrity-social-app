package com.sotroid.post_service.service;

import com.sotroid.post_service.dto.ResponseDTOs.MediaUploadResponse;
import com.sotroid.post_service.util.MultipartInputStreamFileResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

@Service
public class MediaClientService {
    private final RestTemplate restTemplate;

    @Value("${media.service.url}")
    private String mediaServiceUrl;

    public MediaClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String uploadToMediaService(MultipartFile file) {
        ResponseEntity<MediaUploadResponse> response = null;
        try {
            System.out.println("mediaServiceUrl" + mediaServiceUrl);
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", new MultipartInputStreamFileResource(file.getInputStream(), file.getOriginalFilename()));
            body.add("folder", "posts");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

            response = restTemplate.postForEntity(
                    mediaServiceUrl + "/api/media/upload",
                    request,
                    MediaUploadResponse.class
            );

        } catch (Exception e) {
            System.out.println("Failed to upload image via media-service :- " + e);
        }

        return Objects.requireNonNull(response.getBody()).getUrl();
    }
}