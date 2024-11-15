package com.example.utilizadoresService.Exceptions;

public class InexistentImage extends RuntimeException {
    public InexistentImage(String message) {
        super("Imagem inexistente " + message);
    }
}
