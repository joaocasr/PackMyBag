package com.exemplo.encomendaService.services;
import com.exemplo.encomendaService.dto.EncomendaDTO;
import com.exemplo.encomendaService.mapper.EncomendaMapper;
import com.exemplo.encomendaService.model.Cliente;
import com.exemplo.encomendaService.model.Encomenda;
import com.exemplo.encomendaService.repositories.EncomendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EncomendaService {

    private EncomendaRepository encomendaRepository;

    // Criar ou atualizar encomenda
    public EncomendaDTO saveEncomenda(EncomendaDTO encomendaDTO) {
        Encomenda encomenda = EncomendaMapper.toEntity(encomendaDTO);
        encomenda = encomendaRepository.save(encomenda);
        return EncomendaMapper.toDTO(encomenda);
    }

    // Buscar encomenda por ID
    public EncomendaDTO findEncomendaById(int id) {
        Optional<Encomenda> encomenda = encomendaRepository.findById(id);
        return encomenda.map(EncomendaMapper::toDTO).orElse(null);
    }

    // Buscar todas as encomendas
    public List<EncomendaDTO> findAllEncomendas() {
        return encomendaRepository.findAll().stream()
                .map(EncomendaMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Deletar encomenda por ID
    public void deleteEncomenda(int id) {
        encomendaRepository.deleteById(id);
    }
}