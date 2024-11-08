package com.example.recomendacoesservice.exceptions;

public class InexistentRecommendationID extends Exception {
    public InexistentRecommendationID() {
        super("Recommendation not found");
    }
}
