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

    @GetMapping("/pedidosE/{IDEstilista}")
    public List<pedidoDTO> getPedidosEstilista(@PathVariable int IDEstilista, @RequestParam int page, @RequestParam int number){
        try{
            return recomendacoesService.getPedidosEstilista(IDEstilista, page, number);
        }catch(InexistentRequests | InexistentStylistID e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/pedidosC/{IDCliente}")
    public List<pedidoDTO> getPedidosCliente(@PathVariable int IDCliente, @RequestParam int page, @RequestParam int number){
        try{
            return recomendacoesService.getPedidosCliente(IDCliente, page, number);
        }catch(InexistentRequests | InexistentClientID e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/recomendacoesE/{IDEstilista}")
    public List<recomendacaoDTO> getRecomendacoesEstilista(@PathVariable int IDEstilista, @RequestParam int page, @RequestParam int number){
        try{
            return recomendacoesService.getRecomendacoesEstilista(IDEstilista, page, number);
        }catch(InexistentRecommendations | InexistentStylistID e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/recomendacoesC/{IDCliente}")
    public List<recomendacaoDTO> getRecomendacoesCliente(@PathVariable int IDCliente, @RequestParam int page, @RequestParam int number){
        try{
            return recomendacoesService.getRecomendacoesCliente(IDCliente, page, number);
        }catch(InexistentRecommendations | InexistentClientID e){
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
        } catch (InexistentClientID | InexistentStylistID e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/recomendacoes")
    public ResponseEntity<?> newRecomendacao(@RequestBody recomendacaoToClienteDTO recomendacaoToCliente) {
        try {
            recomendacoesService.newRecomendacao(recomendacaoToCliente);
            return ResponseEntity.status(200).body("New Recommendation added with success!"); // 201 Created on success
        } catch (InexistentClientID | InexistentStylistID e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }catch(CompleteWithoutItems e){
            return ResponseEntity.status(409).body(e.getMessage());
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + e.getMessage());
        }
    }

    @DeleteMapping("/recomendacaoRemove/{IDRecomendacao}")
    public ResponseEntity<?> removeRecomendacao(@PathVariable int IDRecomendacao){
        try {
            recomendacoesService.removeRecomendacao(IDRecomendacao);
            return ResponseEntity.status(200).body("Recommendation removed with success!"); // 201 Created on success
        } catch (InexistentRecommendationID e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }catch(RecommendationAlreadyCompleted e){
            return ResponseEntity.status(409).body(e.getMessage());
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/recomendacaoEdit")
    public ResponseEntity<?> editRecomendacao(@RequestBody editRecomendacaoDTO editRecomendacao){
        try {
            recomendacoesService.editRecomendacao(editRecomendacao);
            return ResponseEntity.status(200).body("Recommendation edited with success!"); // 201 Created on success
        }catch(InexistentRecommendationID | NoItems e){
            return ResponseEntity.status(404).body(e.getMessage());
        }catch(RecommendationAlreadyCompleted | CompleteWithoutItems e){
            return ResponseEntity.status(409).body(e.getMessage());
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + e.getMessage());
        }
    }

}
