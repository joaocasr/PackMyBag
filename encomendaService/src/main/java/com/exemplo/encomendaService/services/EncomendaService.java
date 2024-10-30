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

import jakarta.persistence.EntityNotFoundException;

import com.exemplo.encomendaService.repositories.ClienteRepository;

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

    @Autowired
    private ClienteRepository clienteRepository;

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


    // public EncomendaDTO saveEncomenda(EncomendaDTO encomendaDTO) {

    //     // Procura o cliente e lança exceção se não existir
    //     Cliente cliente = clienteRepository.findById(encomendaDTO.getClienteId())
    //             .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

    //     // Converte DTO para entidade usando o mapper
    //     Encomenda encomenda = EncomendaMapper.toEntity(encomendaDTO, cliente);
        
    //     // Salva a encomenda
    //     Encomenda savedEncomenda = encomendaRepository.save(encomenda);

    //     // Converte entidade para DTO e retorna
    //     return EncomendaMapper.toDTO(savedEncomenda);
    // }
    public EncomendaDTO saveEncomenda(EncomendaDTO encomendaDTO) {
        // Procura o cliente
        Cliente cliente = clienteRepository.findById(encomendaDTO.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        // Procura a loja
        Loja loja = lojaRepository.findById(encomendaDTO.getLojaId())
                .orElseThrow(() -> new IllegalArgumentException("Loja não encontrada"));

        // Converte o DTO para entidade Encomenda
        Encomenda encomenda = EncomendaMapper.toEntity(encomendaDTO, cliente);

        // Adiciona a encomenda ao conjunto de encomendas da loja
        loja.getEncomendas().add(encomenda);

        // Salva a loja (o que salvará a encomenda junto com a loja)
        lojaRepository.save(loja);

        // Converte de volta para DTO e retorna
        return EncomendaMapper.toDTO(encomenda);
    }


    public EncomendaDTO updateEncomenda(EncomendaDTO encomendaDTO) {
        
        // Verificar se a encomenda existe
        Encomenda existingEncomenda = encomendaRepository.findById(encomendaDTO.getIdEncomenda())
                .orElseThrow(() -> new EntityNotFoundException("Encomenda não encontrada"));

        // Obter o cliente associado à encomenda
        Cliente cliente = clienteRepository.findById(encomendaDTO.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente inválido"));

        // Usar o mapper para atualizar a entidade com os dados do DTO
        EncomendaMapper.updateEntityFromDTO(existingEncomenda, encomendaDTO, cliente);

        // Salvar alterações
        Encomenda updatedEncomenda = encomendaRepository.save(existingEncomenda);

        // Retornar o DTO atualizado
        return EncomendaMapper.toDTO(updatedEncomenda);
    }



    public void deleteEncomenda(int id) {
        if (!encomendaRepository.existsById(id)) {
            throw new IllegalArgumentException("Encomenda não encontrada");
        }
        encomendaRepository.deleteById(id);
    }
}