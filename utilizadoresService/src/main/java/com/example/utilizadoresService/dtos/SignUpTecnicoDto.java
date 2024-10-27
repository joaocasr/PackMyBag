package com.example.utilizadoresService.dtos;

public record SignUpTecnicoDto(
        String username,
        String nome,
        String password,
        String nomeLoja,
        String email) {
}