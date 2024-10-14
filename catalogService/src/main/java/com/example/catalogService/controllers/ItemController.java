package com.example.catalogService.controllers;

import com.example.catalogService.dto.CatalogoItemDTO;
import com.example.catalogService.dto.PecaInsertDTO;
import com.example.catalogService.dto.SetInsertDTO;
import com.example.catalogService.exceptions.*;
import com.example.catalogService.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/catalogo")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService){this.itemService=itemService;}

    @GetMapping("/")
    public List<CatalogoItemDTO> getItemsByPage(@RequestParam int page, @RequestParam int number){
        try{
            return itemService.getItemsByPage(page,number);
        }catch (NoCatalogItemsException n){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,n.getMessage());
        }
    }

    @GetMapping("/all")
    public List<CatalogoItemDTO> getAllItems(){
        try{
            return itemService.getAllItems();
        }catch (NoCatalogItemsException n){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,n.getMessage());
        }
    }

    @GetMapping("/genero/{gender}")
    public List<CatalogoItemDTO> getPerGenderItems(@RequestParam int page, @RequestParam int number,@PathVariable String gender){
        try{
            return itemService.getPerGenderItems(page,number,gender);
        }catch (NoCatalogItemsGenderException n){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,n.getMessage());
        }
    }

    @GetMapping("/genero/{gender}/price")
    public List<CatalogoItemDTO> getPerPriceandGenderItems(@RequestParam int min, @RequestParam int max,@PathVariable String gender,@RequestParam int page, @RequestParam int number){
        try{
            return itemService.getPerPriceandGenderItems(page,number, min, max,gender);
        }catch (NoCatalogItemsGenderException n){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,n.getMessage());
        }
    }

    @GetMapping("/price")
    public List<CatalogoItemDTO> getPerPriceItems(@RequestParam int page, @RequestParam int number,@RequestParam int min, @RequestParam int max){
        try{
            return itemService.getPerPriceItems(page,number,min,max);
        }catch (NoCatalogItemsPriceException n){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,n.getMessage());
        }
    }

    @PostMapping("/addItem/Peca")
    public ResponseEntity<?> addPecaShop(@RequestBody PecaInsertDTO itemBody){
        try {
            itemService.savePeca(itemBody);
            return ResponseEntity.ok().body("Peca added with sucess!");
        } catch (ItemCodeAlreadyExists e) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addItem/Set")
    public ResponseEntity<?> addSetShop(@RequestBody SetInsertDTO itemBody){
        try {
            itemService.saveSet(itemBody);
            return ResponseEntity.ok().body("Set added with sucess!");
        } catch (ItemCodeAlreadyExists e) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}

