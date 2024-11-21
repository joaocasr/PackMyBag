package com.example.recomendacoesservice.exceptions;

public class RequestNameAlreadyExists extends Exception {
    public RequestNameAlreadyExists() {
        super("Given request name already exists");
    }
}
