package com.example.notificacoesService.dto;


public class EncomendaInsertDTO {

    private String codigoEncomenda;
    private String status;
    private String usernameCliente;
    private String nomeCliente;
    private String emailCliente;

    // Construtor vazio
    public EncomendaInsertDTO() {}

    // Construtor com parâmetros
    public EncomendaInsertDTO( String codigoEncomenda, String nomeCliente, String Status, String usernameCliente, String emailCliente) {
        this.codigoEncomenda = codigoEncomenda;
        this.status = Status;
        this.nomeCliente = nomeCliente;
        this.usernameCliente = usernameCliente;
        this.emailCliente = emailCliente;
    }

    public String getCodigoEncomenda() {
        return codigoEncomenda;
    }

    public void setCodigoEncomenda(String CodigoEncomenda) {
        this.codigoEncomenda = CodigoEncomenda;
    }

    public void nomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String Status) {
        this.status = Status;
    }

    public String getUsernameCliente() {
        return usernameCliente;
    }

    public void setUsernameCliente(String usernameCliente) {
        this.usernameCliente = usernameCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    // toString

    @Override
    public String toString() {
        return "EncomendaDTO{" +
                ", CodigoEncomenda='" + codigoEncomenda + '\'' +
                ", Status='" + status + '\'' +
                ", UsernameCliente='" + usernameCliente + '\'' +
                ", EmailCliente='" + emailCliente + '\'' +
                '}';
    }
}
