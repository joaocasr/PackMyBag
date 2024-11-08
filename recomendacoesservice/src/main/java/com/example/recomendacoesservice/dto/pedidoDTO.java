package com.example.recomendacoesservice.dto;

import com.example.recomendacoesservice.model.Pedido;

public class pedidoDTO {
    private String estilos;
    private String cores;
    private int nrOutfits;
    private double orcamento;
    private String peçasExcluidas;
    private String fabricsPreferences;
    private String occasions;

    public pedidoDTO(Pedido pedido) {
        this.estilos = pedido.getEstilos();
        this.cores = pedido.getCores();
        this.nrOutfits = pedido.getNrOutfits();
        this.orcamento = pedido.getOrcamento();
        this.peçasExcluidas = pedido.getPeçasExcluidas();
        this.fabricsPreferences = pedido.getFabricsPreferences();
        this.occasions = pedido.getOccasions();
    }

    public pedidoDTO( String es, String crs, int nO, double orc, String pe, String fp, String oc) {
        this.estilos = es;
        this.cores = crs;
        this.nrOutfits = nO;
        this.orcamento = orc;
        this.peçasExcluidas = pe;
        this.fabricsPreferences = fp;
        this.occasions = oc;
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
