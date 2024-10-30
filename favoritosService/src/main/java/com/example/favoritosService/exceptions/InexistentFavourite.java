package com.example.favoritosService.exceptions;

public class InexistentFavourite extends Exception{
    public InexistentFavourite(){super("The item does not belong to your favourites.");}
}
