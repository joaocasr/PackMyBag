package com.exemplo.encomendaService.services;
import com.exemplo.encomendaService.dto.EncomendaDTO;
import com.exemplo.encomendaService.dto.ItemDTO;
import com.exemplo.encomendaService.dto.LojaDTO;
import com.exemplo.encomendaService.dto.ClienteDTO;
import com.exemplo.encomendaService.mapper.ItemMapper;
import com.exemplo.encomendaService.mapper.LojaMapper;
import com.exemplo.encomendaService.mapper.EncomendaMapper;
import com.exemplo.encomendaService.mapper.ClienteMapper;
import com.exemplo.encomendaService.model.Cliente;
import com.exemplo.encomendaService.model.Loja;
import com.exemplo.encomendaService.model.Encomenda;

import com.exemplo.encomendaService.repositories.EncomendaRepository;
import com.exemplo.encomendaService.repositories.LojaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EncomendaService {

    @Autowired
    private EncomendaRepository encomendaRepository;

    @Autowired
    private LojaRepository lojaRepository;

    // Procurar encomenda por ID
    // public EncomendaDTO findEncomendaById(int id) {
    //     Encomenda encomenda = encomendaRepository.getIDEncomenda(id);
    //     if (encomenda != null) {
    //         return EncomendaMapper.toDTO(encomenda);
    //     } else {
    //         return null;/items/{idEncomenda}
    //     }
    // }

    public EncomendaDTO findEncomendaById(int id) {
        //Encomenda encomenda = encomendaRepository.getIDEncomenda(id);
        Optional<Encomenda> encomenda = encomendaRepository.findById(id);
        if (encomenda.isPresent()) {
            return EncomendaMapper.toDTO(encomenda.get());
        } else {
            return null;
        }
    }

    // Procurar por CodigoEncomenda 
    public EncomendaDTO findEncomendaByCodigoEncomenda(String codoEncomenda){
        Optional<Encomenda> encomenda = encomendaRepository.findByCodigoEncomenda(codoEncomenda);
        if (encomenda.isPresent()) {
            return EncomendaMapper.toDTO(encomenda.get());
        } else {
            return null;
        }
    }

    // Procurar por Dataentrega ( é uma lista)
    public List<EncomendaDTO> findEncomendaByDataEntrega(String dataEntrega){
        List<Encomenda> encomenda = encomendaRepository.findByDataEntrega(dataEntrega);
        if (encomenda != null) {
            return encomenda.stream()
                    .map(EncomendaMapper::toDTO)
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }

    // Procurar por DataDevolucao ( é uma lista)
    public List<EncomendaDTO> findEncomendaByDataDevolucao(String dataDevolucao){
        List<Encomenda> encomenda = encomendaRepository.findByDataDevolucao(dataDevolucao);
        if (encomenda != null) {
            return encomenda.stream()
                    .map(EncomendaMapper::toDTO)
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }

    // Procurar por LocalEntrega é uma lista
    public List<EncomendaDTO> findEncomendaByLocalEntrega(String localEntrega){
        List<Encomenda> encomenda = encomendaRepository.findByLocalEntrega(localEntrega);
        if (encomenda != null) {
            return encomenda.stream()
                    .map(EncomendaMapper::toDTO)
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }

    // Procurar por Status ( é uma lista )
    public List<EncomendaDTO> findEncomendaByStatus(String status){
        List<Encomenda> encomenda = encomendaRepository.findByStatus(status);
        if (encomenda != null) {
            return encomenda.stream()
                    .map(EncomendaMapper::toDTO)
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }

    // Procurar todas as encomendas de uma loja realizadas pelo um cliente
    public List<EncomendaDTO> getEncomendasCliente(int clienteId) {
        return encomendaRepository.getEncomendasCliente(clienteId).stream()
                .map(EncomendaMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Procura todas as encomendas de uma loja pelo ID da loja
    public List<EncomendaDTO> getEncomendasLoja(int lojaId) {
        Optional<Loja> lojaOpt = lojaRepository.findById(lojaId);

        if (lojaOpt.isPresent()) {
            Loja loja = lojaOpt.get();
            return loja.getEncomendas().stream()
                    .map(EncomendaMapper::toDTO)
                    .collect(Collectors.toList());
        } else {
            return List.of(); 
        }
    }

    // Método para procurar todos os itens de uma encomenda pelo ID da encomenda
    public List<ItemDTO> findItemsByEncomendaId(int encomendaId) {
        Optional<Encomenda> encomendaOpt = encomendaRepository.findById(encomendaId);

        if (encomendaOpt.isPresent()) {
            Encomenda encomenda = encomendaOpt.get();
            // Acessa o conjunto de itens diretamente
            return encomenda.getItems().stream()
                    .map(ItemMapper::toDTO) 
                    .collect(Collectors.toList());
        } else {
            return List.of(); // Retorna uma lista vazia se a encomenda não for encontrada
        }
    }

    // Metodo para encontrar o cliente de uma encomenda pelo ID da encomenda
    public ClienteDTO findClienteByEncomendaId(int encomendaId) {
        Optional<Encomenda> encomendaOpt = encomendaRepository.findById(encomendaId);

        if (encomendaOpt.isPresent()) {
            Encomenda encomenda = encomendaOpt.get();
            return ClienteMapper.toDTO(encomenda.getCliente());
        } else {
            return null;
        }
    }

    public List<EncomendaDTO> getEncomendasByClienteAndLoja(int clienteId, int lojaId) {
        // Procura a loja e suas encomendas
        Optional<Loja> lojaOpt = lojaRepository.findLojaWithEncomendas(lojaId);
        
        if (lojaOpt.isEmpty()) {
            return List.of(); // Retorna lista vazia se a loja não existir
        }

        // Filtra as encomendas da loja pelo ID do cliente
        List<Encomenda> encomendasCliente = encomendaRepository.getEncomendasCliente(clienteId);

        return encomendasCliente.stream()
                .filter(encomenda -> lojaOpt.get().getEncomendas().contains(encomenda)).map(EncomendaMapper::toDTO).
                collect(Collectors.toList());
    }

    //---
    // public List<EncomendaDTO> findAllEncomendas() {
    //     return encomendaRepository.getAllEncomendasRepository().stream()
    //             .map(EncomendaMapper::toDTO)
    //             .collect(Collectors.toList());
    // }

    // Procurar todas as encomeendas
    public List<EncomendaDTO> findAllEncomendas() {
        return encomendaRepository.findAll().stream()
                .map(EncomendaMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Criar ou atualizar encomenda
    public EncomendaDTO saveEncomenda(EncomendaDTO encomendaDTO) {
        Encomenda encomenda = EncomendaMapper.toEntity(encomendaDTO);
        encomenda = encomendaRepository.save(encomenda);
        return EncomendaMapper.toDTO(encomenda);
    }

    // Deletar encomenda por ID
    public void deleteEncomenda(int id) {
        encomendaRepository.deleteById(id);
    }
}