package com.example.recomendacoesservice.exceptions;

public class InexistentRequestName extends Exception {
    public InexistentRequestName() {
        super("Request name invalid");
    }
}
