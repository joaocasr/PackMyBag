package com.example.catalogService.exceptions;

public class NoCatalogItemsGenderException extends Exception{
    public NoCatalogItemsGenderException(String gender){
        super("There are no items with gender "+gender);
    }
}
