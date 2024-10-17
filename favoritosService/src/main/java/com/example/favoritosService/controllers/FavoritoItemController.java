package com.example.favoritosService.controllers;

import com.example.favoritosService.dto.ClienteDTO;
import com.example.favoritosService.dto.FavoritoItemDTO;
import com.example.favoritosService.dto.FavoritoItemInsertDTO;
import com.example.favoritosService.dto.FavoritoItemRemoveDTO;
import com.example.favoritosService.exceptions.*;
import com.example.favoritosService.services.FavoritosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/favoritos")
public class FavoritoItemController {

    private final FavoritosService favItemService;

    @Autowired
    public FavoritoItemController(FavoritosService favoritosService){this.favItemService=favoritosService;}


    @GetMapping("/genero/{gender}")
    //public List<FavoritoItemDTO> getPerGenderItems(@RequestParam int page, @RequestParam int number,@PathVariable String gender){
    public List<FavoritoItemDTO> getPerGenderItems(@RequestBody String username, @PathVariable String gender){
        try{
            return favItemService.getPerGenderItems(username, gender);
        }catch (NoCatalogItemsGenderException n){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,n.getMessage());
        }
    }

    @GetMapping("/size")
    //public List<FavoritoItemDTO> getPerPriceItems(@RequestParam int page, @RequestParam int number,@RequestParam int min, @RequestParam int max){
    public List<FavoritoItemDTO> getPerPriceItems(@RequestBody String username, @RequestParam int min, @RequestParam int max){
        try{
            return favItemService.getPerPriceItems(username, min, max);
        }catch (NoCatalogItemsPriceException n){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,n.getMessage());
        }
    }

    @GetMapping("/price")
    //public List<FavoritoItemDTO> getPerPriceItems(@RequestParam int page, @RequestParam int number,@RequestParam int min, @RequestParam int max){
    public List<FavoritoItemDTO> getPerSizeItems(@RequestBody String username, @RequestParam String size){
        try{
            return favItemService.getPerSizeItems(username, size);
        }catch (NoCatalogItemsPriceException n){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,n.getMessage());
        }
    }

    @PostMapping("/addItem")
    public ResponseEntity<?> addItemFavorite(@RequestBody FavoritoItemInsertDTO itemBody){
        try {
            favItemService.addFavorito(itemBody);
            return ResponseEntity.ok().body("New favorite added with sucess!");
        } catch (ItemCodeAlreadyExists e) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/removeItem")
    public ResponseEntity<?> removePecaFavorite(@RequestBody FavoritoItemRemoveDTO itemBody){
        try {
            favItemService.removeFavorito(itemBody);
            return ResponseEntity.ok().body("Favorite removed with sucess!");
        } catch (ItemCodeAlreadyExists e) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public List<FavoritoItemDTO> getUserFavsByPage(@RequestBody ClienteDTO clienteDTO, @RequestParam int page, @RequestParam int number){
        try{
            return favItemService.getUserFavourites(clienteDTO,page,number);
        }catch (NoCatalogItemsException n){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,n.getMessage());
        }
    }


}

