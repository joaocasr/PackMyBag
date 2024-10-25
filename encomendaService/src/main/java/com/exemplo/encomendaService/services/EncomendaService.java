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

    //@Autowired // isto faz o mesmo que EncomendaRepository encomendaRepository = new EncomendaRepository();
    private EncomendaRepository encomendaRepository;

    //@Autowired
    private EncomendaMapper encomendaMapper;  // Injeção do Mapper

    // Buscar todas as encomendas como DTOs
    public List<EncomendaDTO> getAllEncomendas() {
        return encomendaRepository.findAll()
                .stream()
                .map(encomendaMapper::toDTO)  // Usando o mapper para converter para DTO
                .collect(Collectors.toList());
    }

    // Buscar uma encomenda específica pelo ID
    public Optional<EncomendaDTO> getEncomendaById(int id) {
        return encomendaRepository.findById(id)
                .map(encomendaMapper::toDTO);  // Usando o mapper para converter para DTO
    }

    // Salvar uma nova encomenda (recebe um DTO e converte para entidade)
    public EncomendaDTO saveEncomenda(EncomendaDTO encomendaDTO, Cliente cliente) {
        Encomenda encomenda = encomendaMapper.toEntity(encomendaDTO, cliente);  // Usando o mapper para converter para entidade
        Encomenda savedEncomenda = encomendaRepository.save(encomenda);
        return encomendaMapper.toDTO(savedEncomenda);  // Convertendo a entidade salva para DTO
    }

    // Excluir encomenda
    public void deleteEncomenda(int id) {
        encomendaRepository.deleteById(id);
    }
}
