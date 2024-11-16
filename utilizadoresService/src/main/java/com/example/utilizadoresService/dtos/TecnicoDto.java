package com.example.utilizadoresService.dtos;

import com.example.utilizadoresService.model.Loja;

public record TecnicoDto(
        String username,
        String profileImage,
        String nome,
        Loja loja) {
}
