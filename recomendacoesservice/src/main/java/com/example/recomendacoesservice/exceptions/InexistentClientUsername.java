package com.example.recomendacoesservice.exceptions;

public class InexistentClientUsername extends Exception {
    public InexistentClientUsername(){super("This Client Username does not exist.");}
}
