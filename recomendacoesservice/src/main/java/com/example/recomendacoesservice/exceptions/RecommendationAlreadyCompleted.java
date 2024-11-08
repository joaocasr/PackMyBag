package com.example.recomendacoesservice.exceptions;

public class RecommendationAlreadyCompleted extends Exception {
    public RecommendationAlreadyCompleted(String message) {
        super("Completed recommendations cannot be " + message + ".");
    }
}
