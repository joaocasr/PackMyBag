package com.example.recomendacoesservice.dto;

import com.example.recomendacoesservice.model.Pedido;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class pedidoInfoDTO {
    private String nome;
    private String estilos;
    private String cores;
    private int nrOutfits;
    private double orcamento;
    private String peçasExcluidas;
    private String fabricsPreferences;
    private String occasions;

    public pedidoInfoDTO(String nome, String estilos, String cores, int nrOutfits, double orcamento, String pe, String fp, String oc) {
        this.nome = nome;
        this.estilos = estilos;
        this.cores = cores;
        this.nrOutfits = nrOutfits;
        this.orcamento = orcamento;
        this.fabricsPreferences = fp;
        this.occasions = oc;
        this.peçasExcluidas = pe;
    }

    public pedidoInfoDTO(Pedido p){
        this.nome = p.getNome();
        this.estilos = p.getEstilos();
        this.cores = p.getCores();
        this.nrOutfits = p.getNrOutfits();
        this.orcamento = p.getOrcamento();
        this.fabricsPreferences = p.getFabricsPreferences();
        this.occasions = p.getOccasions();
        this.peçasExcluidas = p.getPeçasExcluidas();
    }

    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
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
