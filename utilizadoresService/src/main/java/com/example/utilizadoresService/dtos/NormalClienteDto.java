package com.example.utilizadoresService.dtos;

public record NormalClienteDto(
        String username,
        String profileImage,
        String nome,
        String morada,
        String cartaoCredito,
        String nrTelemovel,
        String genero) {
}
