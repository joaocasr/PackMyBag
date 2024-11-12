package com.example.catalogService.exceptions;

public class ItemUnavailableException extends Exception{
    public ItemUnavailableException(String designacao, String codigo, String nome ){
        super("The quantity you entered for the item "+designacao+" with code "+codigo+" from the store "+nome+" exceeds the number available.");
    }

}
