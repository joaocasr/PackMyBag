package com.example.recomendacoesservice.exceptions;

public class RequestCompletedPendingPayed extends Exception {
    public RequestCompletedPendingPayed(String status, String message) {
        super(status + " requests cannot " + message + ".");
    }
}
