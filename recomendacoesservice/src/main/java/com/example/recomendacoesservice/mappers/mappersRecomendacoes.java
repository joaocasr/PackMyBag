package com.example.recomendacoesservice.mappers;

import com.example.recomendacoesservice.dto.pedidoDTO;
import com.example.recomendacoesservice.dto.pedidoInfoDTO;
import com.example.recomendacoesservice.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class mappersRecomendacoes {
    public pedidoDTO pedidoToDTO(Pedido pedido) {
        return new pedidoDTO(pedido);
    }
    public pedidoInfoDTO pedidoToInfoDTO(Pedido pedido) { return new pedidoInfoDTO(pedido); }
}
