package com.example.recomendacoesservice.exceptions;

public class IllegalStatus extends Exception {
    public IllegalStatus(String message) {
        super("Illegal status " + message);
    }
}
