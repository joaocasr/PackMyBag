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

    // Procurar encomenda por ID
    public EncomendaDTO findEncomendaById(int id) {
        Encomenda encomenda = encomendaRepository.getIDEncomenda(id);
        if (encomenda != null) {
            return EncomendaMapper.toDTO(encomenda);
        } else {
            return null;
        }
    }

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