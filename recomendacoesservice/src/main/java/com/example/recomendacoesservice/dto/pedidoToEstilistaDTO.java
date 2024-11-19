package com.example.recomendacoesservice.dto;

public class pedidoToEstilistaDTO {
    private String usernameEstilista;
    private String usernameCliente;
    private String estilos;
    private String cores;
    private int nrOutfits;
    private double orcamento;
    private String peçasExcluidas;
    private String fabricsPreferences;
    private String occasions;

    public pedidoToEstilistaDTO(String usernameEstilista, String usernameCliente, String estilos, String cores, int nrOutfits, double orcamento, String pe, String fp, String oc) {
        this.usernameEstilista = usernameEstilista;
        this.usernameCliente = usernameCliente;
        this.estilos = estilos;
        this.cores = cores;
        this.nrOutfits = nrOutfits;
        this.orcamento = orcamento;
        this.fabricsPreferences = fp;
        this.occasions = oc;
        this.peçasExcluidas = pe;
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
