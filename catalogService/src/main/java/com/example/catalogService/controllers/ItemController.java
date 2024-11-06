package com.example.catalogService.controllers;

import com.example.catalogService.dto.*;
import com.example.catalogService.exceptions.*;
import com.example.catalogService.model.Item;
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


    @PostMapping("/items/{id}/addreview")
    public ResponseEntity<?> adicionaReview(@RequestBody InsertReviewDTO insertReviewDTO, @PathVariable int id){
        try{
            itemService.insertReview(insertReviewDTO,id);
            return ResponseEntity.status(200).body("Review adicionada com sucesso!");
        }catch (InexistentItemException i){
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND,i.getMessage()),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/items/{id}/delreview/{username}")
    public ResponseEntity<?> removeReview(@PathVariable int id, @PathVariable String username){
        try{
            itemService.removeReview(id,username);
            return ResponseEntity.ok("removido com sucesso!");
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
            return ResponseEntity.status(200).body("Peca adicionada com sucesso!");
        } catch (ItemCodeAlreadyExists e) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addItem/Set")
    public ResponseEntity<?> addSetShop(@RequestBody SetInsertDTO itemBody){
        try {
            itemService.saveSet(itemBody);
            return ResponseEntity.status(200).body("Set adicionado com sucesso!");
        } catch (ItemCodeAlreadyExists | InexistentItemCodeException e) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addItem/Calcado")
    public ResponseEntity<?> addCalcadoShop(@RequestBody CalcadoInsertDTO itemBody){
        try {
            itemService.saveCalcado(itemBody);
            return ResponseEntity.status(200).body("Calçado adicionado com sucesso!");
        } catch (ItemCodeAlreadyExists e) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/deleteItem")
    public ResponseEntity<?> deleteItem(@RequestBody RemoveItemDTO removeItemDTO) {
        try{
            itemService.removeItem(removeItemDTO);
            return ResponseEntity.status(200).body("Item eliminado com sucesso!");
        }catch (InexistentItemCodeException i){
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND,i.getMessage()),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/editItem")
    public ResponseEntity<?> editItem(@RequestBody EditItemDTO editItemDTO) {
        try {
            boolean r = itemService.editsaveItem(editItemDTO);
            //enviar o objeto pelo broker caso a disponibilidade modifique
            if (r) producerService.sendMessage(new ItemUpdate(editItemDTO.getIdLoja(), editItemDTO.getCodigo(), editItemDTO.getDisponibilidade()));
            return ResponseEntity.status(200).body("Item editado com sucesso.");
        } catch (InexistentItemCodeException e) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/disponibilidade/")
    public ResponseEntity<?> decreaseAvailability(@RequestBody EncomendaDTO encomendaDTO) {
        try{
            List<Item> l = itemService.decreaseAvailability(encomendaDTO);
            if(!l.isEmpty()) {
                for(Item i : l){
                    producerService.sendMessage(new ItemUpdate(i.getLoja().getIDLoja(),i.getCodigo(),i.getDisponibilidade()));
                }
            }
            return ResponseEntity.status(200).body("Encomenda realizada com sucesso. O item do catálogo foi atualizado.");
        }catch (ItemUnavailableException i){
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND,i.getMessage()),HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/random")
    public List<CatalogoItemDTO> getRandomItemsHome() {
        return itemService.getRandomItems();
    }

    @GetMapping("/trending/{lojaid}")
    public List<TrendingItemDTO> getTrendingItems(@PathVariable int lojaid) {
        return itemService.getTrendingItems(lojaid);
    }
}

