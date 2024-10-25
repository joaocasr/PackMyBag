package com.exemplo.encomendaService.mapper;

import org.springframework.stereotype.Component;

import com.exemplo.encomendaService.dto.EncomendaDTO;
import com.exemplo.encomendaService.model.Encomenda;

@Component
public class EncomendaMapper {

    // Converte de Entidade para DTO
    public static EncomendaDTO toDTO(Encomenda encomenda) {
        EncomendaDTO dto = new EncomendaDTO();
        dto.setIdEncomenda(encomenda.getIDEncomenda());
        dto.setCodigoEncomenda(encomenda.getCodigoEncomenda());
        dto.setDataEntrega(encomenda.getDataEntrega());
        dto.setDevolucao(encomenda.getDevolucao());
        dto.setLocalEntrega(encomenda.getLocalEntrega());
        dto.setStatus(encomenda.getStatus());
        dto.setClienteId(encomenda.getCliente().getIDCliente());
        return dto;
    }

    // Converte de DTO para Entidade
    public static Encomenda toEntity(EncomendaDTO dto) {
        Encomenda encomenda = new Encomenda();
        encomenda.setCodigoEncomenda(dto.getCodigoEncomenda());
        encomenda.setDataEntrega(dto.getDataEntrega());
        encomenda.setDevolucao(dto.getDevolucao());
        encomenda.setLocalEntrega(dto.getLocalEntrega());
        encomenda.setStatus(dto.getStatus());
        // Precisa de setar o cliente separadamente (relacionamento ManyToOne)
        return encomenda;
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
