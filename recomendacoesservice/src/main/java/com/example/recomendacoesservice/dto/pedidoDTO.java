package com.example.recomendacoesservice.dto;

import com.example.recomendacoesservice.model.Pedido;
import com.example.recomendacoesservice.dto.itemDTO;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class pedidoDTO {
    private int IDPedido;
    private String estilos;
    private String cores;
    private int nrOutfits;
    private double orcamento;
    private String peçasExcluidas;
    private String fabricsPreferences;
    private String occasions;
    private String descricao;
    private Set<itemDTO> conjunto;
    private String status;

    public pedidoDTO(Pedido pedido) {
        this.IDPedido = pedido.getIDPedido();
        this.estilos = pedido.getEstilos();
        this.cores = pedido.getCores();
        this.nrOutfits = pedido.getNrOutfits();
        this.orcamento = pedido.getOrcamento();
        this.peçasExcluidas = pedido.getPeçasExcluidas();
        this.fabricsPreferences = pedido.getFabricsPreferences();
        this.occasions = pedido.getOccasions();
        this.descricao = pedido.getDescricao();
        this.conjunto = pedido.getConjunto().stream().map(x -> new itemDTO(x)).collect(Collectors.toSet());
        this.status = pedido.getStatus();
    }

    public pedidoDTO(int IDPedido, String estilos, String cores, int nrOutfits, double orcamento, String peçasExcluidas, String fabricsPreferences, String occasions, String descricao, Set<itemDTO> conjunto, String status) {
        this.IDPedido = IDPedido;
        this.estilos = estilos;
        this.cores = cores;
        this.nrOutfits = nrOutfits;
        this.orcamento = orcamento;
        this.peçasExcluidas = peçasExcluidas;
        this.fabricsPreferences = fabricsPreferences;
        this.occasions = occasions;
        this.descricao = descricao == null ? "" : descricao;
        this.conjunto = conjunto == null ? new HashSet<>() : conjunto;
        this.status = status;
    }

    public int getIDPedido() {
        return this.IDPedido;
    }
    public void setIDPedido(int IDPedido) {
        this.IDPedido = IDPedido;
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

    public String getDescricao() {
        return this.descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Set<itemDTO> getConjunto() {
        return conjunto;
    }
    public void setConjunto(Set<itemDTO> conjunto) {
        this.conjunto = conjunto;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
