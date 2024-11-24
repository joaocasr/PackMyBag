package com.example.utilizadoresService.dtos;

import org.springframework.web.multipart.MultipartFile;

public record EditTecnicoProfileDto (
        String username,
        String newName,
        String newEmail,
        MultipartFile newProfileImage) {
}
