package com.example.utilizadoresService.dtos;

import org.springframework.web.multipart.MultipartFile;

public record UploadProfileImageDto(String username, MultipartFile profile_image) {
}
