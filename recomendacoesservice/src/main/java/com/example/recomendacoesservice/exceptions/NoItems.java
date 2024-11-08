package com.example.recomendacoesservice.exceptions;

public class NoItems extends Exception {
    public NoItems(String message) {
        super("No selected " + message);
    }
}
