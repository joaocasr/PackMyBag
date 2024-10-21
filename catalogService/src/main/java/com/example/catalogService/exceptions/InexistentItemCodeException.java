package com.example.catalogService.exceptions;

public class InexistentItemCodeException extends Exception{
    public InexistentItemCodeException(String code){
        super("inexistent item with code "+code);
    }
}
