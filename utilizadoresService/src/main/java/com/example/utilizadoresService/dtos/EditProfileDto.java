package com.example.utilizadoresService.dtos;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public record EditProfileDto(
        String username,
        String newName,
        String newEmail,
        MultipartFile newProfileImage,
        Map<String, Object> additionalFields) {
}
