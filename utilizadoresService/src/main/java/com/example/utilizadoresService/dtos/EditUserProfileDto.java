package com.example.utilizadoresService.dtos;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public record EditUserProfileDto(
        String username,
        String newName,
        String newEmail,
        MultipartFile newProfileImage,
        String morada,
        String cartaoCredito,
        String nrTelemovel,
        String genero) {
}
