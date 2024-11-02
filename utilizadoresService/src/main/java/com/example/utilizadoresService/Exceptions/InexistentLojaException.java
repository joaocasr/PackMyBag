package com.example.utilizadoresService.Exceptions;

public class InexistentLojaException extends RuntimeException {
    public InexistentLojaException(String message) {
        super("Loja inexistente " + message);
    }
}
