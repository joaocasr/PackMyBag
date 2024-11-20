package com.example.recomendacoesservice.repositories;

import com.example.recomendacoesservice.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface pedidosRepository extends JpaRepository<Pedido, Integer> {
    @Query("select p FROM Pedido p where p.nome = :nome")
    Optional<Pedido> getPedido(@Param("nome") String nome);

    @Query("SELECT p FROM Pedido p WHERE p.cliente.IDCliente = :IDCliente AND p.status = 'completed'")
    Page<Pedido> getPedidosByCliente(@Param("IDCliente") Integer IDCliente, PageRequest pageRequest);
}
