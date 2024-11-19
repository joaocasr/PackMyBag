package com.example.recomendacoesservice.exceptions;

public class CompleteWithoutItemsOrDescription extends Exception {
    public CompleteWithoutItemsOrDescription() {
        super("Cannot set request as 'complete' without any items or description.");
    }
}
