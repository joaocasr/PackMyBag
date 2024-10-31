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

import org.springframework.kafka.core.KafkaTemplate;
import com.exemplo.encomendaService.dto.EncomendaNotificationDTO;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
public class EncomendaService {

    @Autowired
    private EncomendaRepository encomendaRepository;

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    // Procurar por ID
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

        List<Encomenda> encomendasCliente = encomendaRepository.getEncomendasCliente(clienteId);

        return encomendasCliente.stream()
                .filter(encomenda -> lojaOpt.get().getEncomendas().contains(encomenda)).map(EncomendaMapper::toDTO).
                collect(Collectors.toList());
    }

    // Procurar todas as encomeendas
    public List<EncomendaDTO> findAllEncomendas() {
        return encomendaRepository.findAll().stream()
                .map(EncomendaMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Método para salvar uma nova encomenda
    public EncomendaDTO saveEncomenda(EncomendaDTO encomendaDTO) {

        Cliente cliente = clienteRepository.findById(encomendaDTO.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        Loja loja = lojaRepository.findById(encomendaDTO.getLojaId())
                .orElseThrow(() -> new IllegalArgumentException("Loja não encontrada"));

        Encomenda encomenda = EncomendaMapper.toEntity(encomendaDTO, cliente);

        loja.getEncomendas().add(encomenda);

        lojaRepository.save(loja);

        return EncomendaMapper.toDTO(encomenda);
    }

    // Método para atualizar uma encomenda
    public EncomendaDTO updateEncomenda(EncomendaDTO encomendaDTO) {
        
        Encomenda existingEncomenda = encomendaRepository.findById(encomendaDTO.getIdEncomenda())
                .orElseThrow(() -> new EntityNotFoundException("Encomenda não encontrada"));

        Cliente cliente = clienteRepository.findById(encomendaDTO.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente inválido"));

        EncomendaMapper.updateEntityFromDTO(existingEncomenda, encomendaDTO, cliente);

        Encomenda updatedEncomenda = encomendaRepository.save(existingEncomenda);

        return EncomendaMapper.toDTO(updatedEncomenda);
    }

    // Método para deletar uma encomenda
    public void deleteEncomenda(int id) {
        if (!encomendaRepository.existsById(id)) {
            throw new IllegalArgumentException("Encomenda não encontrada");
        }
        encomendaRepository.deleteById(id);
    }

    // Método para contar o número total de encomendas
    public long countEncomendas() {
        return encomendaRepository.count();
    }

    // Método para contar o número de itens em uma encomenda específica
    public long countItensInEncomenda(int id) {
        Encomenda encomenda = encomendaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Encomenda não encontrada"));
        return encomenda.getItems().size();
    }

    // Contar o número de encomendas de um cliente específico
    public long countEncomendasByCliente(int clienteId) {
        return encomendaRepository.getEncomendasCliente(clienteId).size();
    }

    // Contar o número de encomendas de uma loja específica
    public long countEncomendasByLoja(int lojaId) {
        Optional<Loja> lojaOpt = lojaRepository.findById(lojaId);
        return lojaOpt.map(loja -> loja.getEncomendas().size()).orElse(0);
    }

    // Método agendado para rodar diariamente e verificar as datas de devolução
    @Scheduled(cron = "0 0 9 * * ?")  // Executa todos os dias às 9 da manhã
    public void verifydate() {
        LocalDate hoje = LocalDate.now();  // Define a data atual
        //LocalDate dataLimite = hoje.plusDays(3);  // Define a data limite como 3 dias a partir da data atual
    
        List<Encomenda> encomendas = encomendaRepository.findAll();  // Recupera todas as encomendas do repositório
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // Formato para interpretar as datas como String
    
        for (Encomenda encomenda : encomendas) {  // Itera sobre cada encomenda
            if (encomenda.getDataDevolucao() != null) {  // Verifica se a encomenda possui uma data de devolução
                LocalDate dataDevolucao = LocalDate.parse(encomenda.getDataDevolucao(), formatter);  // Converte a data de devolução de String para LocalDate
    
                // Verifica se a data de devolução está dentro do intervalo de 3 dias a partir de hoje
                if (!dataDevolucao.isBefore(hoje) && ChronoUnit.DAYS.between(hoje, dataDevolucao) <= 3) {
                    // Calcula o tempo restante para a devolução
                    long tempoRestante = ChronoUnit.DAYS.between(hoje, dataDevolucao);
                    // Envia mensagem para o Kafka
                    kafkaProducerService.sendMessage(EncomendaMapper.toEncomendaNotifcationDTO(encomenda, tempoRestante));
                }
            }
        }
    }
    
}