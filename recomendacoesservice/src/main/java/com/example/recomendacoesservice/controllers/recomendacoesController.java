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
        } catch (InexistentClientUsername | InexistentStylistUsername e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + e.getMessage());
        }
    }

    @PatchMapping("/pedidoEdit")
    public ResponseEntity<?> editPedido(@RequestBody editPedidoDTO editPedido){
        try {
            recomendacoesService.editPedido(editPedido);
            return ResponseEntity.status(200).body("Recommendation edited with success!"); // 201 Created on success
        }catch(InexistentRequestID | NoItems | UnknownEditType e){
            return ResponseEntity.status(404).body(e.getMessage());
        }catch(RequestAlreadyCompleted | CompleteWithoutItemsOrDescription e){
            return ResponseEntity.status(409).body(e.getMessage());
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + e.getMessage());
        }
    }
}
