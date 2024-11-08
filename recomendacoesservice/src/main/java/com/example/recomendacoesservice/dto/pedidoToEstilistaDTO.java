package com.example.recomendacoesservice.dto;

public class pedidoToEstilistaDTO {
    private int IDEstilista;
    private int IDCliente;
    private String estilos;
    private String cores;
    private int nrOutfits;
    private double orcamento;
    private String peçasExcluidas;
    private String fabricsPreferences;
    private String occasions;

    public pedidoToEstilistaDTO(int IDEstilista, int IDCliente, String estilos, String cores, int nrOutfits, double orcamento, String pe, String fp, String oc) {
        this.IDEstilista = IDEstilista;
        this.IDCliente = IDCliente;
        this.estilos = estilos;
        this.cores = cores;
        this.nrOutfits = nrOutfits;
        this.orcamento = orcamento;
        this.fabricsPreferences = fp;
        this.occasions = oc;
        this.peçasExcluidas = pe;
    }

    public int getIDEstilista() {
        return IDEstilista;
    }
    public void setIDEstilista(int iDEstilista) {
        IDEstilista = iDEstilista;
    }
    public int getIDCliente() {
        return IDCliente;
    }
    public void setIDCliente(int iDECliente) {
        IDCliente = iDECliente;
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
