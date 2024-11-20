package com.example.recomendacoesservice.exceptions;

public class ItemAlreadyAdded extends Exception {
    public ItemAlreadyAdded() {
        super("Item already added");
    }
}
