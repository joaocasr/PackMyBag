package com.exemplo.encomendaService.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.encomendaService.dto.ClienteDTO;
import com.exemplo.encomendaService.dto.EncomendaDTO;
import com.exemplo.encomendaService.dto.EncomendaStatusDTO;
import com.exemplo.encomendaService.dto.ItemDTO;
import com.exemplo.encomendaService.dto.LojaDTO;
import com.exemplo.encomendaService.services.EncomendaService;
import com.exemplo.encomendaService.services.LojaService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/encomendas")
public class EncomendaController {

    @Autowired // para subsittuir isto tenho de inciializar o encomenda service = private EncomendaService encomendaService = new EncomendaService();
    private EncomendaService encomendaService;

    @Autowired
    private LojaService lojaService;


    // Endpoint para procurar todas as encomendas
    @GetMapping("/all")
    public ResponseEntity<?> getAllEncomendas() {
        try {
            List<EncomendaDTO> encomendas = encomendaService.findAllEncomendas();
            return new ResponseEntity<>(encomendas, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace(); // Log do erro para identificar o problema
            return new ResponseEntity<>("Erro ao procurar encomendas: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para procurar uma encomenda por ID
    @GetMapping("/{id}")
    public ResponseEntity<EncomendaDTO> getEncomendaById(@PathVariable int id) {
        EncomendaDTO encomendaDTO = encomendaService.findEncomendaById(id);
        if (encomendaDTO != null) {
            return new ResponseEntity<>(encomendaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint para procurar uma encomenda por código de encomenda
    @GetMapping("/codigo/{codigoEncomenda}")
    public ResponseEntity<EncomendaDTO> getEncomendaByCodigoEncomenda(@PathVariable String codigoEncomenda) {
        EncomendaDTO encomendaDTO = encomendaService.findEncomendaByCodigoEncomenda(codigoEncomenda);
        if (encomendaDTO != null) {
            return new ResponseEntity<>(encomendaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint para procurar encomendas por data de entrega
    @GetMapping("/dataEntrega/{dataEntrega}")
    public ResponseEntity<List<EncomendaDTO>> getEncomendasByDataEntrega(@PathVariable String dataEntrega) {
        List<EncomendaDTO> encomendas = encomendaService.findEncomendaByDataEntrega(dataEntrega);
        return new ResponseEntity<>(encomendas, HttpStatus.OK);
    }

    // Endpoint para procurar encomendas por data de devolução
    @GetMapping("/dataDevolucao/{dataDevolucao}")
    public ResponseEntity<List<EncomendaDTO>> getEncomendasByDataDevolucao(@PathVariable String dataDevolucao) {
        List<EncomendaDTO> encomendas = encomendaService.findEncomendaByDataDevolucao(dataDevolucao);
        return new ResponseEntity<>(encomendas, HttpStatus.OK);
    }

    // Endpoint para procurar encomendas por local de entrega
    @GetMapping("/localEntrega/{localEntrega}")
    public ResponseEntity<List<EncomendaDTO>> getEncomendasByLocalEntrega(@PathVariable String localEntrega) {
        List<EncomendaDTO> encomendas = encomendaService.findEncomendaByLocalEntrega(localEntrega);
        return new ResponseEntity<>(encomendas, HttpStatus.OK);
    }

    // Endpoint para procurar encomendas por estado
    @GetMapping("/status/{status}")
    public ResponseEntity<List<EncomendaDTO>> getEncomendasByEstado(@PathVariable String status) {
        List<EncomendaDTO> encomendas = encomendaService.findEncomendaByStatus(status);
        return new ResponseEntity<>(encomendas, HttpStatus.OK);
    }

    // Endpoint para procurar todos as encomendas de um cliente
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<EncomendaDTO>> getEncomendasByCliente(@PathVariable int idCliente) {
        List<EncomendaDTO> encomendas = encomendaService.getEncomendasCliente(idCliente);
        return new ResponseEntity<>(encomendas, HttpStatus.OK);
    }

    // Endpoint para procurar todas as encomendas de uma loja
    @GetMapping("/loja/{idLoja}")
    public ResponseEntity<List<EncomendaDTO>> getEncomendasByLoja(@PathVariable int idLoja) {
        List<EncomendaDTO> encomendas = encomendaService.getEncomendasLoja(idLoja);
        return new ResponseEntity<>(encomendas, HttpStatus.OK);
    }

    // Endpoint para procurar a loja referente a uma encomenda
    @GetMapping("/loja/encomenda/{idEncomenda}")
    public ResponseEntity<LojaDTO> getLojaByEncomenda(@PathVariable int idEncomenda) {
        LojaDTO loja = lojaService.getLojaByEncomendaId(idEncomenda);
        if (loja != null) {
            return new ResponseEntity<>(loja, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint para procurar o cliente referente a uma encomenda
    @GetMapping("/cliente/encomenda/{idEncomenda}")
    public ResponseEntity<ClienteDTO> getClienteByEncomenda(@PathVariable int idEncomenda) {
        ClienteDTO cliente = encomendaService.findClienteByEncomendaId(idEncomenda);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // Endpoint para procurar todos os itens de uma encomenda
    @GetMapping("/items/{idEncomenda}")
    public ResponseEntity<List<ItemDTO>> getItemsByEncomenda(@PathVariable int idEncomenda) {
        List<ItemDTO> items = encomendaService.findItemsByEncomendaId(idEncomenda);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    // Endpoint para procurar encomendas de um cliente numa loja
    @GetMapping("/cliente/{idCliente}/loja/{idLoja}")
    public ResponseEntity<List<EncomendaDTO>> getEncomendasClienteLoja(@PathVariable int idCliente, @PathVariable int idLoja) {
        List<EncomendaDTO> encomendas = encomendaService.getEncomendasByClienteAndLoja(idCliente, idLoja);
        return new ResponseEntity<>(encomendas, HttpStatus.OK);
    }

    // Endpoint para criar ou atualizar uma encomenda
    @PostMapping
    public ResponseEntity<?> createOrUpdateEncomenda(@RequestBody EncomendaDTO encomendaDTO) {
        encomendaService.saveEncomenda(encomendaDTO);
        return new ResponseEntity<>("Encomendas updated in shops", HttpStatus.CREATED);
    }

    // Endpoint para criar uma nova encomenda
    @PostMapping("/create")
    public ResponseEntity<?> createEncomenda(@RequestBody EncomendaDTO encomendaDTO) {
        try{
            encomendaService.saveEncomenda(encomendaDTO);
            return new ResponseEntity<>("Encomendas saved in shops", HttpStatus.CREATED); // Retorna status 201 Created
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para atualizar uma encomenda
    @PutMapping("/update")
    public ResponseEntity<EncomendaDTO> updateEncomenda(@RequestBody EncomendaDTO encomendaDTO) {

        EncomendaDTO updatedDTO = encomendaService.updateEncomenda(encomendaDTO);
        return ResponseEntity.ok(updatedDTO);
    }

    // Endpoint para deletar uma encomenda pelo ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEncomenda(@PathVariable int id) {
        encomendaService.deleteEncomenda(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retorna status 204 No Content
    }

    // Endpoint para contar o número total de encomendas
    @GetMapping("/count")
    public ResponseEntity<Long> countEncomendas() {
        try {
            long count = encomendaService.countEncomendas();
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para contar o número de itens em uma encomenda específica
    @GetMapping("/count/itens/{id}")
    public ResponseEntity<Long> countItensInEncomenda(@PathVariable int id) {
        try {
            long itemCount = encomendaService.countItensInEncomenda(id);
            return new ResponseEntity<>(itemCount, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para contar o número de encomendas de uma loja
    @GetMapping("/count/loja/{idLoja}")
    public ResponseEntity<Long> countEncomendasByLoja(@PathVariable int idLoja) {
        try {
            long count = encomendaService.countEncomendasByLoja(idLoja);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para contar o número de encomendas de um cliente
    @GetMapping("/count/cliente/{idCliente}")
    public ResponseEntity<Long> countEncomendasByCliente(@PathVariable int idCliente) {
        try {
            long count = encomendaService.countEncomendasByCliente(idCliente);
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/status/{codigo}/{novoStatus}")
    public ResponseEntity<?> updateEncomendaStatus(
            @PathVariable String codigo,
            @PathVariable String novoStatus) {
        try {
            EncomendaStatusDTO updatedStatus = encomendaService.updateEncomendaStatus(codigo, novoStatus);
            return new ResponseEntity<>(updatedStatus, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Erro: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Erro: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para procurar encomendas por username do cliente
    @GetMapping("/cliente/username/{username}")
    public ResponseEntity<List<EncomendaDTO>> getEncomendasByClienteUsername(@PathVariable String username) {
        List<EncomendaDTO> encomendas = encomendaService.findEncomendasByClienteUsername(username);
        return new ResponseEntity<>(encomendas, HttpStatus.OK);
    }

        // **Novo Endpoint para buscar uma encomenda específica por username e código da encomenda**
    @GetMapping("/cliente/username/{username}/codigoEncomenda/{codigoEncomenda}")
    public ResponseEntity<EncomendaDTO> getEncomendaByClienteUsernameAndCodigoEncomenda(
            @PathVariable String username,
            @PathVariable String codigoEncomenda) {
        try {
            EncomendaDTO encomenda = encomendaService.findEncomendaByClienteUsernameAndCodigoEncomenda(username, codigoEncomenda);
            return new ResponseEntity<>(encomenda, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/loja/nome/{nomeLoja}")
    public ResponseEntity<List<EncomendaDTO>> getEncomendasPorNomeLoja(@PathVariable String nomeLoja) {
        List<EncomendaDTO> encomendas = encomendaService.getEncomendasPorNomeLoja(nomeLoja);
        
        if (encomendas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<>(encomendas, HttpStatus.OK);
    }

}