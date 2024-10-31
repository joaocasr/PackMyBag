package com.exemplo.encomendaService.dto;

public class EncomendaNotificationDTO {

    private int idEncomenda;
    private String codigoEncomenda;
    private String dataEntrega;
    private String dataDevolucao;
    private String localEntrega;
    private int clienteId;
    private long tempoRestante;

    public EncomendaNotificationDTO() {
    }

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

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public long getTempoRestante() {
        return tempoRestante;
    }

    public void setTempoRestante(long tempoRestante) {
        this.tempoRestante = tempoRestante;
    }

    public String toString() {
        return "EncomendaNotificationDTO{" +
                "idEncomenda=" + idEncomenda +
                ", codigoEncomenda='" + codigoEncomenda + '\'' +
                ", dataEntrega='" + dataEntrega + '\'' +
                ", dataDevolucao='" + dataDevolucao + '\'' +
                ", localEntrega='" + localEntrega + '\'' +
                ", clienteId=" + clienteId +
                ", tempoRestante=" + tempoRestante +
                '}';
    }


    
}
