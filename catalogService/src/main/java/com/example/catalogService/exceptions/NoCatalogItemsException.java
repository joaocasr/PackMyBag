package com.example.catalogService.exceptions;
public class NoCatalogItemsException extends Exception {
    public NoCatalogItemsException(){
        super("No items found in this page!");
    }
}
