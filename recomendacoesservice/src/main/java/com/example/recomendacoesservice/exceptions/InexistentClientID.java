package com.example.recomendacoesservice.exceptions;

public class InexistentClientID extends Exception {
    public InexistentClientID(){super("This Client ID does not exist.");}
}
