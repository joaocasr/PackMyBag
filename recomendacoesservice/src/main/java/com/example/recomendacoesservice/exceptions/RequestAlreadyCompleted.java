package com.example.recomendacoesservice.exceptions;

public class RequestAlreadyCompleted extends Exception {
    public RequestAlreadyCompleted(String message) {
        super("Completed requests cannot be " + message + ".");
    }
}
