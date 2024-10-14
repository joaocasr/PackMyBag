package com.example.catalogService.exceptions;

public class ItemCodeAlreadyExists extends Exception{
    public ItemCodeAlreadyExists(String code){
        super("Item with code "+code+" already exists!");
    }
}
