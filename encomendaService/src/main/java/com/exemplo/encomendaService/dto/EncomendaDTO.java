package com.exemplo.encomendaService.dto;

import java.util.Set;

public class EncomendaDTO {
    
    private String codigoEncomenda;
    private String dataEntrega;
    private String dataDevolucao;
    private String localEntrega;
    private String status;
    private String clienteUsername;
    private String clienteNome;
    private String clienteEmail;
    private int lojaId;
    // FALTA o set com os itens da encomenda
    private Set<ItemDTO> itens;
    private Double preco;
    private Double taxaEntrega;
    
    public EncomendaDTO() {
    }

    public String getCodigoEncomenda() {
        return codigoEncomenda;
    }

    public void setCodigoEncomenda(String codigoEncomenda) {
        this.codigoEncomenda = codigoEncomenda;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public String getLocalEntrega() {
        return localEntrega;
    }

    public void setLocalEntrega(String localEntrega) {
        this.localEntrega = localEntrega;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClienteUsername() {
        return clienteUsername;
    }

    public void setClienteUsername(String clienteusername) {
        this.clienteUsername = clienteusername;
    }

    public int getLojaId() {
        return lojaId;
    }

    public void setLojaId(int lojaId) {
        this.lojaId = lojaId;
    }

    public Set<ItemDTO> getItens() {
        return itens;
    }

    public void setItens(Set<ItemDTO> itens) {
        this.itens = itens;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public String getClienteEmail() {
        return clienteEmail;
    }

    public void setClienteEmail(String clienteEmail) {
        this.clienteEmail = clienteEmail;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getTaxaEntrega() {
        return taxaEntrega;
    }

    public void setTaxaEntrega(Double taxaEntrega) {
        this.taxaEntrega = taxaEntrega;
    }
}
