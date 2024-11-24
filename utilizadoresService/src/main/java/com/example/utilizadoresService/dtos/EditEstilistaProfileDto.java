package com.example.utilizadoresService.dtos;

import org.springframework.web.multipart.MultipartFile;

public record EditEstilistaProfileDto (
        String username,
        String newName,
        String newEmail,
        MultipartFile newProfileImage,
        String bio ){
        }
