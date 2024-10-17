package com.example.favoritosService.exceptions;
public class NoCatalogItemsException extends Exception {
    public NoCatalogItemsException(){
        super("No items found in this page!");
    }
}
