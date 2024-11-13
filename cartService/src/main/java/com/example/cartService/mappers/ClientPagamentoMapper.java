package com.example.cartService.mappers;

import org.springframework.stereotype.Component;
import com.example.cartService.dto.PagamentoDTO;
import com.example.cartService.model.Pagamento;

@Component
public class ClientPagamentoMapper {

    public static PagamentoDTO toPagamentoDTO(Pagamento pagamento) {
        return new PagamentoDTO(
            pagamento.getCodigo(), 
            pagamento.getTotal(), 
            pagamento.getLocalEntrega(), 
            pagamento.getInicioAluguer(), 
            pagamento.getFimAluguer(), 
            pagamento.getModoPagamento(), 
            pagamento.getdataGeracao(),
            pagamento.getStatus());
    }
}
