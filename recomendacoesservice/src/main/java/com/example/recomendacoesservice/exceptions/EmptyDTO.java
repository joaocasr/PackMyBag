package com.example.recomendacoesservice.exceptions;

public class EmptyDTO extends Exception {
    public EmptyDTO() {
        super("Received object has an empty body");
    }
}
