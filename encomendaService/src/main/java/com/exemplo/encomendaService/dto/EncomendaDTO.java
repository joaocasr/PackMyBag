package com.exemplo.encomendaService.dto;

public class EncomendaDTO {
    
    private int idEncomenda;
    private String codigoEncomenda;
    private String dataEntrega;
    private String dataDevolucao;
    private String localEntrega;
    private String status;
    private int clienteId;
    private int lojaId;
    
    public EncomendaDTO() {
    }
    
    //Getters and Setters
    public int getIdEncomenda() {
        return idEncomenda;
    }

    public void setIdEncomenda(int idEncomenda) {
        this.idEncomenda = idEncomenda;
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

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getLojaId() {
        return lojaId;
    }

    public void setLojaId(int lojaId) {
        this.lojaId = lojaId;
    }
}
