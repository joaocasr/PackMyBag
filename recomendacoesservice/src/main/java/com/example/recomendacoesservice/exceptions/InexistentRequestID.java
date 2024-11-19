package com.example.recomendacoesservice.exceptions;

public class InexistentRequestID extends Exception {
    public InexistentRequestID() {
        super("Request ID invalid");
    }
}
