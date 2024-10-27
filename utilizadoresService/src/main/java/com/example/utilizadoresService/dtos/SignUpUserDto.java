package com.example.utilizadoresService.dtos;

public record SignUpUserDto(
        String username,
        String password,
        String nome,
        String email) {
}
