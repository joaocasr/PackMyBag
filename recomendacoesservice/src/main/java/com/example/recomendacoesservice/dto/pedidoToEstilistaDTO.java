package com.example.recomendacoesservice.dto;

public class pedidoToEstilistaDTO {
    private String usernameEstilista;
    private String usernameCliente;
    private String nome;
    private String estilos;
    private String cores;
    private int nrOutfits;
    private double orcamento;
    private String peçasExcluidas;
    private String fabricsPreferences;
    private String occasions;

    public pedidoToEstilistaDTO(String usernameEstilista, String usernameCliente, String nome, String estilos, String cores, int nrOutfits, double orcamento, String pe, String fp, String oc) {
        this.usernameEstilista = usernameEstilista == null? "" : usernameEstilista;
        this.usernameCliente = usernameCliente == null? "" : usernameCliente;
        this.nome = nome == null? "" : nome;
        this.estilos = estilos == null? "" : estilos;
        this.cores = cores == null? "" : cores;
        this.nrOutfits = nrOutfits;
        this.orcamento = orcamento;
        this.fabricsPreferences = fp == null? "" : fp;
        this.occasions = oc == null? "" : oc;
        this.peçasExcluidas = pe == null? "" : pe;
    }

    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public String getUsernameEstilista() {
        return this.usernameEstilista;
    }
    public void setUsernameEstilista(String usernameEstilista) {
        this.usernameEstilista = usernameEstilista;
    }
    public String getUsernameCliente() {
        return this.usernameCliente;
    }
    public void setUsernameCliente(String usernameCliente) {
        this.usernameCliente = usernameCliente;
    }
    public String getEstilos() {
        return estilos;
    }
    public void setEstilos(String estilos) {
        this.estilos = estilos;
    }
    public String getCores() {
        return cores;
    }
    public void setCores(String cores) {
        this.cores = cores;
    }
    public void setNrOutfits(int value) {
        this.nrOutfits = value;
    }

    public int getNrOutfits() {
        return this.nrOutfits;
    }

    public void setOrcamento(double value) {
        this.orcamento = value;
    }

    public double getOrcamento() {
        return this.orcamento;
    }

    public String getFabricsPreferences() {
        return this.fabricsPreferences;
    }

    public void setFabricsPreferences(String fabricsPreferences) {
        this.fabricsPreferences = fabricsPreferences;
    }

    public String getPeçasExcluidas() {
        return this.peçasExcluidas;
    }

    public void setPeçasExcluidas(String peçasExcluidas) {
        this.peçasExcluidas = peçasExcluidas;
    }

    public String getOccasions() {
        return this.occasions;
    }

    public void setOccasions(String occasions) {
        this.occasions = occasions;
    }

}
