package com.example.catalogService.controllers;

import com.example.catalogService.dto.*;
import com.example.catalogService.exceptions.*;
import com.example.catalogService.services.ItemService;
import com.example.catalogService.services.ProducerService;
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
    private final ProducerService producerService;

    @Autowired
    public ItemController(ItemService itemService, ProducerService producerService){
        this.itemService=itemService;
        this.producerService=producerService;
    }

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

    @GetMapping("/items/{id}")
    public ResponseEntity<?> showItemDetails(@PathVariable int id){
        try{
            return ResponseEntity.ok(itemService.getItem(id));
        }catch (InexistentItemException i){
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND,i.getMessage()),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/items/{id}/reviews")
    public List<ReviewDTO> getReviews(@PathVariable int id, @RequestParam int page, @RequestParam int number){
        return itemService.getReviews(id,page,number);
    }


    @PostMapping("/items/{id}/review")
    public ResponseEntity<?> adicionaReview(@RequestBody InsertReviewDTO insertReviewDTO, @PathVariable int id){
        try{
            itemService.insertReview(insertReviewDTO,id);
            return ResponseEntity.ok("adicionado com sucesso!");
        }catch (InexistentItemException i){
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND,i.getMessage()),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/lojas/{lojaid}")
    public List<CatalogoItemDTO> getItemsfromShop(@PathVariable int lojaid,@RequestParam int page, @RequestParam int number){
        try{
            return itemService.getItemsFromShop(lojaid,page,number);
        }catch (NoCatalogItemsException n){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,n.getMessage());
        }
    }

    @GetMapping("/type/{type}")
    public List<CatalogoItemDTO> getPerTypeItems(@RequestParam int page, @RequestParam int number,@PathVariable String type){
        try{
            return itemService.getPerTypeItems(page,number,type);
        }catch (NoCatalogItemsGenderException n){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,n.getMessage());
        }
    }

    @GetMapping("/type/{type}/price")
    public List<CatalogoItemDTO> getPerPriceandTypeItems(@RequestParam int min, @RequestParam int max,@PathVariable String type,@RequestParam int page, @RequestParam int number){
        try{
            return itemService.getPerPriceandTypeItems(page,number, min, max,type);
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

    @PutMapping("/editItem")
    public void editItem(@RequestBody ItemUpdate itemUpdate){
        producerService.sendMessage(itemUpdate);
    }


}

