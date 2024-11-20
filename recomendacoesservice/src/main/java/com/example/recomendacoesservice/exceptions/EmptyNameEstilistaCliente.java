package com.example.recomendacoesservice.exceptions;

public class EmptyNameEstilistaCliente extends Exception {
    public EmptyNameEstilistaCliente() {
        super("Empty Request Name or Stylist username or Client username");
    }
}
