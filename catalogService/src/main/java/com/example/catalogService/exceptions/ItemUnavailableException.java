package com.example.catalogService.exceptions;

public class ItemUnavailableException extends Exception{
    public ItemUnavailableException(String designacao, String codigo, String nome ){
        super("A quantidade que inseriu para o item "+designacao+" com cóidgo "+codigo+" da loja "+nome+" excede o disponível indisponível.");
    }
}
