package com.example.recomendacoesservice.mappers;

import com.example.recomendacoesservice.dto.pedidoDTO;
import com.example.recomendacoesservice.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class mappersRecomendacoes {
    public pedidoDTO pedidoToDTO(Pedido pedido) {
        return new pedidoDTO(pedido);
    }
}
