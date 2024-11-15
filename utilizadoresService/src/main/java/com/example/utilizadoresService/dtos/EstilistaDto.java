package com.example.utilizadoresService.dtos;

import org.springframework.web.multipart.MultipartFile;

public record EstilistaDto(
        String username,
        MultipartFile profileImage,
        String nome,
        String bio,
        int rating) {
}
