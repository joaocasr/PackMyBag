package com.example.utilizadoresService.dtos;

public record SignUpEstilistaDto (
        String username,
        String password,
        String nome,
        String email,
        String genero,
        String bio) {
}
