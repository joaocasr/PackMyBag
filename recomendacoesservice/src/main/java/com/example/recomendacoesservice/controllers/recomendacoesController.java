package com.example.recomendacoesservice.controllers;

import com.example.recomendacoesservice.dto.*;
import com.example.recomendacoesservice.exceptions.*;
import com.example.recomendacoesservice.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/recomendacoes")
public class recomendacoesController {
    private final RecomendacoesService recomendacoesService;

    @Autowired
    public recomendacoesController(RecomendacoesService recomendacoesService) {
        this.recomendacoesService = recomendacoesService;
    }

    @GetMapping("/pedidosE/{username}")
    public List<pedidoDTO> getPedidosEstilista(@PathVariable String username, @RequestParam int page, @RequestParam int number){
        try{
            return recomendacoesService.getPedidosEstilista(username, page, number);
        }catch(InexistentRequests | InexistentStylistUsername e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/pedidosC/{username}")
    public List<recomendacaoToClienteDTO> getPedidosCliente(@PathVariable String username, @RequestParam int page, @RequestParam int number){
        try{
            return recomendacoesService.getPedidosCliente(username, page, number);
        }catch(InexistentRequests | InexistentClientUsername e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/pedidos")
    public ResponseEntity<?> newPedido(@RequestBody pedidoToEstilistaDTO pedidoToEstilista) {
        try {
            recomendacoesService.newPedido(pedidoToEstilista);
            return ResponseEntity.status(200).body("New Request added with success!"); // 201 Created on success
        } catch (InexistentClientUsername | InexistentStylistUsername | EmptyNameEstilistaCliente e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + e.getMessage());
        }
    }

    @DeleteMapping("/removePedido/{nome}")
    public ResponseEntity<?> removePedido(@PathVariable String nome) {
        try{
            recomendacoesService.removePedido(nome);
            return ResponseEntity.status(200).body("Request removed with success!");
        }catch(InexistentRequestName | RequestCompletedPendingPayed e){
            return ResponseEntity.status(404).body(e.getMessage());
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + e.getMessage());
        }
    }

    @PatchMapping("/changeStatusPedido")
    public ResponseEntity<?> changeStatusPedido(@RequestBody statusPedidoDTO statusPedido){
        try{
            recomendacoesService.changeStatusPedido(statusPedido);
            return ResponseEntity.status(200).body("Request Status changed with success!");
        }catch(InexistentRequestName | NoItems | EmptyDTO e){
            return ResponseEntity.status(404).body(e.getMessage());
        }catch(IllegalStatus | RequestCompletedPendingPayed e){
            return ResponseEntity.status(409).body(e.getMessage());
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/addItem")
    public ResponseEntity<?> addItemPedido(@RequestBody addRemoveItemDTO addItem){
        try{
            recomendacoesService.addItemPedido(addItem);
            return ResponseEntity.status(200).body("Item added with success!");
        }catch(InexistentRequestName | EmptyDTO e){
            return ResponseEntity.status(404).body(e.getMessage());
        }catch(ItemAlreadyAdded | RequestCompletedPendingPayed e){
            return ResponseEntity.status(409).body(e.getMessage());
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + e.getMessage());
        }
    }

    @DeleteMapping("/removeItem")
    public ResponseEntity<?> removeItemPedido(@RequestBody addRemoveItemDTO removeItem){
        try{
            recomendacoesService.removeItemPedido(removeItem);
            return ResponseEntity.status(200).body("Item removed with success!");
        }catch(InexistentRequestName | EmptyDTO e){
            return ResponseEntity.status(404).body(e.getMessage());
        }catch(ItemNotAdded | RequestCompletedPendingPayed e){
            return ResponseEntity.status(409).body(e.getMessage());
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + e.getMessage());
        }
    }

    @PatchMapping("/editDescricaoOrCompleteRequest")
    public ResponseEntity<?> editDescricaoOrCompletePedido(@RequestBody editPedidoDTO editPedidoDTO){
        try{
            recomendacoesService.editDescricaoOrCompletePedido(editPedidoDTO);
            return ResponseEntity.status(200).body("Request description edited or Request completed with success!");
        }catch(InexistentRequestName |  NoItems | EmptyDTO e){
            return ResponseEntity.status(404).body(e.getMessage());
        }catch(IllegalStatus | RequestCompletedPendingPayed e){
            return ResponseEntity.status(409).body(e.getMessage());
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + e.getMessage());
        }
    }
}
