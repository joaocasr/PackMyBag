package com.exemplo.encomendaService.services;
import com.exemplo.encomendaService.dto.EncomendaDTO;
import com.exemplo.encomendaService.mapper.EncomendaMapper;
import com.exemplo.encomendaService.model.Cliente;
import com.exemplo.encomendaService.model.Encomenda;
import com.exemplo.encomendaService.repositories.EncomendaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EncomendaService {

    @Autowired
    private EncomendaRepository encomendaRepository;

    // Procurar encomenda por ID
    // public EncomendaDTO findEncomendaById(int id) {
    //     Encomenda encomenda = encomendaRepository.getIDEncomenda(id);
    //     if (encomenda != null) {
    //         return EncomendaMapper.toDTO(encomenda);
    //     } else {
    //         return null;
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