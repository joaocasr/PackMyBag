package com.exemplo.encomendaService.services;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exemplo.encomendaService.dto.ClienteDTO;
import com.exemplo.encomendaService.dto.EncomendaDTO;
import com.exemplo.encomendaService.dto.EncomendaDateReturnDTO;
import com.exemplo.encomendaService.dto.EncomendaStatusDTO;
import com.exemplo.encomendaService.dto.ItemDTO;
import com.exemplo.encomendaService.mapper.ClienteMapper;
import com.exemplo.encomendaService.mapper.EncomendaMapper;
import com.exemplo.encomendaService.mapper.ItemMapper;
import com.exemplo.encomendaService.model.Cliente;
import com.exemplo.encomendaService.model.Encomenda;
import com.exemplo.encomendaService.model.Item;
import com.exemplo.encomendaService.model.Loja;
import com.exemplo.encomendaService.repositories.ClienteRepository;
import com.exemplo.encomendaService.repositories.EncomendaRepository;
import com.exemplo.encomendaService.repositories.LojaRepository;

import jakarta.persistence.EntityNotFoundException;

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
        Optional<Encomenda> encomenda = encomendaRepository.findEncomendaByCode(codoEncomenda);
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
    public void saveEncomenda(EncomendaDTO encomendaDTO) {
        Cliente c;
        Optional<Cliente> cliente = clienteRepository.findByUsername(encomendaDTO.getClienteUsername());
        if(cliente.isEmpty()) {
            c = new Cliente(encomendaDTO.getClienteNome(),encomendaDTO.getClienteEmail(),encomendaDTO.getClienteUsername());
            clienteRepository.save(c);
        }
        else c = cliente.get();
        Map<Integer,List<ItemDTO>> itemsperShop = new HashMap<>();
        for (ItemDTO i : encomendaDTO.getItens()){
            List<ItemDTO> l = new ArrayList<>();
            l.add(i);
            if(!itemsperShop.containsKey(i.getIdLoja())) itemsperShop.put(i.getIdLoja(),l);
            else{
                List<ItemDTO> items = itemsperShop.get(i.getIdLoja());
                items.add(i);
                itemsperShop.put(i.getIdLoja(),items);
            }
        }
        for (Map.Entry<Integer, List<ItemDTO>> entry : itemsperShop.entrySet()) {

            Loja loja = lojaRepository.findById(entry.getKey())
                    .orElseThrow(() -> new IllegalArgumentException("Loja não encontrada"));

            EncomendaDTO newencomenda = encomendaDTO;
            newencomenda.setItens(new HashSet<>(entry.getValue()));
            Encomenda encomenda = EncomendaMapper.toEntity(newencomenda, c);
            
            List<Item> l = entry.getValue().stream().map(x->ItemMapper.toEntity(x)).collect(Collectors.toList());
            for (Item i : l){
                encomenda.addItemToEncomenda(i);
            }

            loja.adicionaEncomenda(encomenda);

            lojaRepository.save(loja);
        }
    }

    // Método para atualizar uma encomenda
    public EncomendaDTO updateEncomenda(EncomendaDTO encomendaDTO) {
        
        Optional<Encomenda> e = encomendaRepository.findEncomendaByCode(encomendaDTO.getCodigoEncomenda());
        Encomenda existingEncomenda = e.get();

        Cliente cliente = clienteRepository.findByUsername(encomendaDTO.getClienteUsername())
                .orElseThrow(() -> new IllegalArgumentException("Cliente inválido"));

        EncomendaMapper.updateEntityFromDTO(existingEncomenda, encomendaDTO, cliente);

        Encomenda updatedEncomenda = encomendaRepository.save(existingEncomenda);

        // Verificar se a dataDevolucao foi atualizada
        if (!existingEncomenda.getDataDevolucao().equals(updatedEncomenda.getDataDevolucao())) {
            LocalTime horaAtual = LocalTime.now();
            LocalTime horaLimite = LocalTime.of(9, 0);

            // Verificar se está a menos de três dias de ser devolvida a encomenda
            LocalDate dataDevolucao = LocalDate.parse(updatedEncomenda.getDataDevolucao(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate hoje = LocalDate.now();

            int diasRestantes = Math.toIntExact(ChronoUnit.DAYS.between(hoje, dataDevolucao));

            if (horaAtual.isAfter(horaLimite) && diasRestantes <= 3) {
                // Notificar via Kafka
                EncomendaDateReturnDTO devolucaoDTO = EncomendaMapper.toEncomendaDateReturnDTO(updatedEncomenda,diasRestantes);
                kafkaProducerService.sendMessage(devolucaoDTO.toString(), "EncomendaDateReturn");
                }
            }

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
    
        List<Encomenda> encomendas = encomendaRepository.findAll();  // Recupera todas as encomendas do repositório
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // Formato para interpretar as datas como String
    
        for (Encomenda encomenda : encomendas) {  // Itera sobre cada encomenda
            if (encomenda.getDataDevolucao() != null) {  // Verifica se a encomenda possui uma data de devolução
                LocalDate dataDevolucao = LocalDate.parse(encomenda.getDataDevolucao(), formatter);  // Converte a data de devolução de String para LocalDate
    
                // Verifica se a data de devolução está dentro do intervalo de 3 dias a partir de hoje
                if (!dataDevolucao.isBefore(hoje) && ChronoUnit.DAYS.between(hoje, dataDevolucao) <= 3) {
                    // Calcula o tempo restante para a devolução
                    int tempoRestante = Math.toIntExact(ChronoUnit.DAYS.between(hoje, dataDevolucao));
                    // Envia mensagem para o Kafka
                    kafkaProducerService.sendMessage(EncomendaMapper.toEncomendaDateReturnDTO(encomenda, tempoRestante).toString(),"EncomendaDateReturn");
                }
            }
        }
    }

    @Transactional
    public EncomendaStatusDTO updateEncomendaStatus(String codigo, String novoStatus) {
        Optional<Encomenda> e = encomendaRepository.findEncomendaByCode(codigo);
        Encomenda encomenda = e.get();
    
        List<String> statusValidos = Arrays.asList("Pago", "Processamento", "Enviado", "Entregue", "Devolvido", "Cancelado");
        if (!statusValidos.contains(novoStatus)) {
            throw new IllegalArgumentException("Status inválido");
        }
    
        if (!encomenda.getStatus().equals(novoStatus)) {
            encomendaRepository.updateStatusByCodigo(encomenda.getCodigoEncomenda(), novoStatus);

            // Enviar mensagem pelo Kafka
            kafkaProducerService.sendMessage(EncomendaMapper.toEncomendaStatusDTO(encomenda).toString(), "EncomendaStatus");
        }   
    
        return EncomendaMapper.toEncomendaStatusDTO(encomenda);
    }

    public List<EncomendaDTO> findEncomendasByClienteUsername(String username) {
        List<Encomenda> encomendas = encomendaRepository.findEncomendasByClienteUsername(username);
        return encomendas.stream().map(EncomendaMapper::toDTO).collect(Collectors.toList());
    }


    public EncomendaDTO findEncomendaByClienteUsernameAndCodigoEncomenda(String clienteUsername, String codigoEncomenda) {
        Optional<Encomenda> encomendaOpt = encomendaRepository.findByClienteUsernameAndCodigoEncomenda(clienteUsername, codigoEncomenda);
        
        if (encomendaOpt.isPresent()) {
            return EncomendaMapper.toDTO(encomendaOpt.get());
        } else {
            throw new EntityNotFoundException("Encomenda não encontrada para o usuário " + clienteUsername + " com o código " + codigoEncomenda);
        }
    }

    public List<EncomendaDTO> getEncomendasPorNomeLoja(String nomeLoja) {
        Optional<Loja> lojaOpt = lojaRepository.findByNome(nomeLoja);
        
        if (lojaOpt.isPresent()) {
            Loja loja = lojaOpt.get();
            return loja.getEncomendas().stream()
                       .map(EncomendaMapper::toDTO)
                       .collect(Collectors.toList());
        } else {
            // Retorna uma lista vazia se a loja não for encontrada
            return List.of();
        }
    }

    
}