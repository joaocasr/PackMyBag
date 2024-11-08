package com.example.recomendacoesservice.exceptions;

public class InexistentRecommendations extends Exception {
    public InexistentRecommendations() {super("No recommendations found");}
}
