package com.example.recomendacoesservice.repositories;

import com.example.recomendacoesservice.model.Estilista;
import com.example.recomendacoesservice.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface estilistasRepository extends JpaRepository<Estilista, String>{
    @Query("SELECT e FROM Estilista e WHERE e.username = :username")
    Optional<Estilista> getEstilista(@Param("username") String username);

    @Query("SELECT e.pedidos FROM Estilista e WHERE e.username = :username")
    Page<Pedido> getPedidosbyEstilista(@Param("username") String username, PageRequest pageRequest);
}
