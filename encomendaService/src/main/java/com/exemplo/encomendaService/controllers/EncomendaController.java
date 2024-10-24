package main.java.com.exemplo.encomendaService.controllers;

import com.exemplo.encomenda.dto.EncomendaDTO;
import com.exemplo.encomenda.service.EncomendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/encomendas")
public class EncomendaController {

    @Autowired
    private EncomendaService encomendaService;

    // Retorna todas as encomendas como DTOs
    @GetMapping
    public List<EncomendaDTO> getAllEncomendas() {
        return encomendaService.getAllEncomendas();
    }

    // Retorna uma encomenda específica pelo ID como DTO
    @GetMapping("/{id}")
    public ResponseEntity<EncomendaDTO> getEncomendaById(@PathVariable int id) {
        Optional<EncomendaDTO> encomendaDTO = encomendaService.getEncomendaById(id);
        return encomendaDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Cria uma nova encomenda
    @PostMapping
    public EncomendaDTO createEncomenda(@RequestBody EncomendaDTO encomendaDTO) {
        return encomendaService.saveEncomenda(encomendaDTO);
    }

    // Exclui uma encomenda pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEncomenda(@PathVariable int id) {
        encomendaService.deleteEncomenda(id);
        return ResponseEntity.noContent().build();
    }
}
