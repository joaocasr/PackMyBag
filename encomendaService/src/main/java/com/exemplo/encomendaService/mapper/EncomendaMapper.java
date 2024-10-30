package com.exemplo.encomendaService.mapper;

import org.springframework.stereotype.Component;

import com.exemplo.encomendaService.dto.EncomendaDTO;
import com.exemplo.encomendaService.model.Cliente;
import com.exemplo.encomendaService.model.Encomenda;

@Component
public class EncomendaMapper {

    // Converte de Entidade para DTO
    public static EncomendaDTO toDTO(Encomenda encomenda) {
        EncomendaDTO dto = new EncomendaDTO();
        dto.setIdEncomenda(encomenda.getIDEncomenda());
        dto.setCodigoEncomenda(encomenda.getCodigoEncomenda());
        dto.setDataEntrega(encomenda.getDataEntrega());
        dto.setDataDevolucao(encomenda.getDataDevolucao());
        dto.setLocalEntrega(encomenda.getLocalEntrega());
        dto.setStatus(encomenda.getStatus());
        dto.setClienteId(encomenda.getCliente().getIDCliente());
        //dto.setLojaId(encomenda.getLoja().getIDLoja()); // Associa a loja
        return dto;
    }

    public static Encomenda toEntity(EncomendaDTO dto,Cliente cliente) {
        Encomenda encomenda = new Encomenda();
        encomenda.setCodigoEncomenda(dto.getCodigoEncomenda());
        encomenda.setDataEntrega(dto.getDataEntrega());
        encomenda.setDataDevolucao(dto.getDataDevolucao());
        encomenda.setLocalEntrega(dto.getLocalEntrega());
        encomenda.setStatus(dto.getStatus());
        encomenda.setCliente(cliente); // Define o cliente na encomenda
        //encomenda.setLojaId(encomenda.getLoja().getIDLoja()); // Associa a loja
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
    }
}


// @Mapper
// public interface EncomendaMapper {

//     EncomendaMapper INSTANCE = Mappers.getMapper(EncomendaMapper.class);

//     // Converte de Encomenda para EncomendaDTO
//     @Mapping(source = "cliente.email", target = "clienteEmail")
//     @Mapping(source = "localEntrega", target = "localEntrega") // Adicione esta linha
//     @Mapping(source = "status", target = "status") // Adicione esta linha
//     EncomendaDTO toDTO(Encomenda encomenda);

//     // Converte de EncomendaDTO para Encomenda
//     @Mapping(source = "clienteEmail", target = "cliente.email")
//     @Mapping(source = "localEntrega", target = "localEntrega") // Adicione esta linha
//     @Mapping(source = "status", target = "status") // Adicione esta linha
//     Encomenda toEntity(EncomendaDTO encomendaDTO);
// }
