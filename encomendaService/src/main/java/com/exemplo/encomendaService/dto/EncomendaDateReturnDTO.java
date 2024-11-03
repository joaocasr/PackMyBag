package com.exemplo.encomendaService.dto;

public class EncomendaDateReturnDTO {

    private String codigoEncomenda;
    private String dataEntrega;
    private String dataDevolucao;
    private String localEntrega;
    private int clienteId;
    private int tempoRestante;

    public EncomendaDateReturnDTO() {
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

    public int getTempoRestante() {
        return tempoRestante;
    }

    public void setTempoRestante(int tempoRestante) {
        this.tempoRestante = tempoRestante;
    }

    public String toString() {
        return "EncomendaNotificationDTO,"
                + codigoEncomenda + ","
                + dataEntrega + ","
                + dataDevolucao + ","
                + localEntrega + ","
                + clienteId + ","
                + tempoRestante ;
    }


    
}
