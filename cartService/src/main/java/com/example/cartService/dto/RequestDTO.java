package com.example.cartService.dto;

public class RequestDTO {
    private String codigo;
    private String status;

    public RequestDTO() {
    }

    public RequestDTO(String codigo, 
                   String status) {
        this.codigo = codigo;
        this.status = status;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
