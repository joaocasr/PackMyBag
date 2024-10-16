package com.example.favoritosService.exceptions;

public class ItemCodeAlreadyExists extends Exception{
    public ItemCodeAlreadyExists(String code){
        super("Item with code "+code+" already exists!");
    }
}
