package com.example.catalogService.exceptions;

public class InexistentItemException extends Exception{
    public InexistentItemException(int id){
        super("The item with id "+String.valueOf(id)+" does not exist!");
    }
}
