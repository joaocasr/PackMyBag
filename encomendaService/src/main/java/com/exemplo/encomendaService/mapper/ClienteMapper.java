package com.exemplo.encomendaService.mapper;

import org.springframework.stereotype.Component;

import com.exemplo.encomendaService.dto.ClienteDTO;
import com.exemplo.encomendaService.model.Cliente;

@Component
public class ClienteMapper {

    public static ClienteDTO toDTO(Cliente cliente) {
        return new ClienteDTO(cliente.getIDCliente(), cliente.getNome(), cliente.getUsername(), cliente.getEmail());
    }

    public static Cliente toEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        //cliente.setIDCliente(clienteDTO.getIDCliente());
        cliente.setNome(clienteDTO.getNome());
        cliente.setUsername(clienteDTO.getUsername());
        cliente.setEmail(clienteDTO.getEmail());
        return cliente;
    }
    
}
