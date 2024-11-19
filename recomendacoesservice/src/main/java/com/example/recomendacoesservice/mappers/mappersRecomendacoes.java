package com.example.recomendacoesservice.mappers;

import com.example.recomendacoesservice.dto.*;
import com.example.recomendacoesservice.model.*;
import org.springframework.stereotype.Component;

@Component
public class mappersRecomendacoes {
    public pedidoDTO pedidoToDTO(Pedido pedido) {
        return new pedidoDTO(pedido);
    }
}
