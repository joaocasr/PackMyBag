package com.example.recomendacoesservice.exceptions;

public class ItemNotAdded extends Exception {
    public ItemNotAdded() {
        super("Item was not added previously");
    }
}
