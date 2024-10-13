package com.example.catalogService.exceptions;

public class NoCatalogItemsPriceException extends Exception{
    public NoCatalogItemsPriceException(){
        super("No items found in that price range!");
    }
}
