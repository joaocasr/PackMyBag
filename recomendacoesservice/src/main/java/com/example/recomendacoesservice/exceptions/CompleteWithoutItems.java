package com.example.recomendacoesservice.exceptions;

public class CompleteWithoutItems extends Exception {
    public CompleteWithoutItems() {
        super("Cannot set recommendation as 'complete' without any items.");
    }
}
