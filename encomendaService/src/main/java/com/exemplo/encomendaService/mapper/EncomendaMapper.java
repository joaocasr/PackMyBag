package com.exemplo.encomendaService.mapper;

import org.springframework.stereotype.Component;

import com.exemplo.encomendaService.dto.EncomendaDTO;
import com.exemplo.encomendaService.model.Cliente;
import com.exemplo.encomendaService.model.Encomenda;
import com.exemplo.encomendaService.dto.EncomendaDateReturnDTO;
import com.exemplo.encomendaService.dto.EncomendaStatusDTO;
import com.exemplo.encomendaService.model.Item;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EncomendaMapper {

    // Converte de Entidade para DTO
    public static EncomendaDTO toDTO(Encomenda encomenda) {
        EncomendaDTO dto = new EncomendaDTO();
        //dto.setIdEncomenda(encomenda.getIDEncomenda());
        dto.setCodigoEncomenda(encomenda.getCodigoEncomenda());
        dto.setDataEntrega(encomenda.getDataEntrega());
        dto.setDataDevolucao(encomenda.getDataDevolucao());
        dto.setLocalEntrega(encomenda.getLocalEntrega());
        dto.setStatus(encomenda.getStatus());
        dto.setClienteUsername(encomenda.getCliente().getUsername());
        dto.setClienteNome(encomenda.getCliente().getNome());
        dto.setClienteEmail(encomenda.getCliente().getEmail());
        //dto.setLojaId(encomenda.getLoja().getIDLoja()); // A loja nao pode ser associada porque estamos a usar classes, e a entidade Loja é que tem uma lista com as encomendas para isto funcionar o lojaid tinha de estar definido na classe encomenda
        dto.setItens(encomenda.getItems().stream().map(ItemMapper::toDTO).collect(Collectors.toSet()));
        dto.setPreco(encomenda.getPreco());
        dto.setTaxaEntrega(encomenda.getTaxaEntrega());
        return dto;
    }

    // Converte de DTO para Entidade
    public static Encomenda toEntity(EncomendaDTO dto,Cliente cliente) {
        Encomenda encomenda = new Encomenda();
        encomenda.setCodigoEncomenda(dto.getCodigoEncomenda());
        encomenda.setDataEntrega(dto.getDataEntrega());
        encomenda.setDataDevolucao(dto.getDataDevolucao());
        encomenda.setLocalEntrega(dto.getLocalEntrega());
        encomenda.setStatus(dto.getStatus());
        encomenda.setCliente(cliente); // Define o cliente na encomenda
        //encomenda.setLojaId(encomenda.getLoja().getIDLoja()); // Associa a loja
        encomenda.setPreco(dto.getPreco());
        encomenda.setTaxaEntrega(dto.getTaxaEntrega());
        return encomenda;
    }

    // Atualiza uma entidade Encomenda existente com os valores de um DTO
    public static void updateEntityFromDTO(Encomenda encomenda, EncomendaDTO dto, Cliente cliente) {
        encomenda.setCodigoEncomenda(dto.getCodigoEncomenda());
        encomenda.setDataEntrega(dto.getDataEntrega());
        encomenda.setDataDevolucao(dto.getDataDevolucao());
        encomenda.setLocalEntrega(dto.getLocalEntrega());
        encomenda.setStatus(dto.getStatus());
        encomenda.setCliente(cliente);
        // o hibernate nao deixa apagar o set de itens e adicionar um novo, por isso temos de limpar o set e adicionar os novos
        encomenda.getItems().clear();
        encomenda.getItems().addAll(dto.getItens().stream().map(ItemMapper::toEntity).collect(Collectors.toSet()));
        encomenda.setPreco(dto.getPreco());
        encomenda.setTaxaEntrega(dto.getTaxaEntrega());
}

    // Converte de Encomenda para EncomendaDateReturnDTO
    public static EncomendaDateReturnDTO toEncomendaDateReturnDTO(Encomenda encomenda, int tempoRestante) {
        EncomendaDateReturnDTO notificationDTO = new EncomendaDateReturnDTO();
        notificationDTO.setCodigoEncomenda(encomenda.getCodigoEncomenda());
        notificationDTO.setDataEntrega(encomenda.getDataEntrega());
        notificationDTO.setDataDevolucao(encomenda.getDataDevolucao());
        notificationDTO.setLocalEntrega(encomenda.getLocalEntrega());
        notificationDTO.setClienteId(encomenda.getCliente().getIDCliente());
        notificationDTO.setTempoRestante(tempoRestante);
        return notificationDTO;
    }

        // Converte de Encomenda para EncomendaStatusDTO
    public static EncomendaStatusDTO toEncomendaStatusDTO(Encomenda encomenda) {
        EncomendaStatusDTO statusDTO = new EncomendaStatusDTO();
        statusDTO.setCodigoEncomenda(encomenda.getCodigoEncomenda());
        statusDTO.setStatus(encomenda.getStatus());
        return statusDTO;
    }

}