package com.exemplo.encomendaService.controllers;

import com.exemplo.encomendaService.dto.EncomendaDTO;
import com.exemplo.encomendaService.services.EncomendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/encomendas")
public class EncomendaController {

    @Autowired
    private EncomendaService encomendaService;

    // Endpoint para buscar todas as encomendas
    @GetMapping("/all")
    public List<EncomendaDTO> getAllEncomendas() {
        return encomendaService.findAllEncomendas();
    }

    // Endpoint para buscar uma encomenda por ID
    @GetMapping("/{id}")
    public ResponseEntity<EncomendaDTO> getEncomendaById(@PathVariable int id) {
        EncomendaDTO encomendaDTO = encomendaService.findEncomendaById(id);
        if (encomendaDTO != null) {
            return new ResponseEntity<>(encomendaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint para criar ou atualizar uma encomenda
    @PostMapping
    public ResponseEntity<EncomendaDTO> createOrUpdateEncomenda(@RequestBody EncomendaDTO encomendaDTO) {
        EncomendaDTO savedEncomenda = encomendaService.saveEncomenda(encomendaDTO);
        return new ResponseEntity<>(savedEncomenda, HttpStatus.CREATED);
    }

    // Endpoint para deletar uma encomenda por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEncomenda(@PathVariable int id) {
        encomendaService.deleteEncomenda(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}