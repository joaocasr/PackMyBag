package com.exemplo.encomendaService.dto;

import java.time.LocalDate;

public class EncomendaDTO {

    private Integer IDEncomenda;
    private String CodigoEncomenda;
    private LocalDate DataEntrega;
    private String LocalEntrega;
    private String Status;

    // Construtor vazio
    public EncomendaDTO() {}

    // Construtor com parâmetros
    public EncomendaDTO(Integer IDEncomenda, String CodigoEncomenda, LocalDate DataEntrega, String LocalEntrega, String Status) {
        this.IDEncomenda = IDEncomenda;
        this.CodigoEncomenda = CodigoEncomenda;
        this.DataEntrega = DataEntrega;
        this.LocalEntrega = LocalEntrega;
        this.Status = Status;
    }

    // Getters e Setters
    public Integer getIDEncomenda() {
        return IDEncomenda;
    }

    public void setIDEncomenda(Integer IDEncomenda) {
        this.IDEncomenda = IDEncomenda;
    }

    public String getCodigoEncomenda() {
        return CodigoEncomenda;
    }

    public void setCodigoEncomenda(String CodigoEncomenda) {
        this.CodigoEncomenda = CodigoEncomenda;
    }

    public LocalDate getDataEntrega() {
        return DataEntrega;
    }

    public void setDataEntrega(LocalDate DataEntrega) {
        this.DataEntrega = DataEntrega;
    }

    public String getLocalEntrega() {
        return LocalEntrega;
    }

    public void setLocalEntrega(String LocalEntrega) {
        this.LocalEntrega = LocalEntrega;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    // toString

    @Override
    public String toString() {
        return "EncomendaDTO{" +
                "IDEncomenda=" + IDEncomenda +
                ", CodigoEncomenda='" + CodigoEncomenda + '\'' +
                ", DataEntrega=" + DataEntrega +
                ", LocalEntrega='" + LocalEntrega + '\'' +
                ", Status='" + Status + '\'' +
                '}';
    }
}
