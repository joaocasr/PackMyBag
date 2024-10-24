package main.java.com.exemplo.encomendaService.mapper;

@Component
public class EncomendaMapper {

    // Converter Entidade Encomenda para DTO
    public EncomendaDTO toDTO(Encomenda encomenda) {
        EncomendaDTO dto = new EncomendaDTO();
        dto.setIdEncomenda(encomenda.getIdEncomenda());
        dto.setClienteEmail(encomenda.getCliente().getEmail());
        dto.setCodigoEncomenda(encomenda.getCodigoEncomenda());
        dto.setDataEntrega(encomenda.getDataEntrega());
        dto.setStatus(encomenda.getStatus());
        return dto;
    }

    // Converter DTO para Entidade Encomenda
    public Encomenda toEntity(EncomendaDTO dto, Cliente cliente) {
        Encomenda encomenda = new Encomenda();
        encomenda.setIdEncomenda(dto.getIdEncomenda());
        encomenda.setCodigoEncomenda(dto.getCodigoEncomenda());
        encomenda.setDataEntrega(dto.getDataEntrega());
        encomenda.setStatus(dto.getStatus());
        encomenda.setCliente(cliente);  // Cliente já carregado
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
