package com.example.recomendacoesservice.repositories;

import com.example.recomendacoesservice.model.Recomendacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.Optional;

@Repository
public interface recomendacoesRepository extends JpaRepository<Recomendacao, Integer>{
    @Query("SELECT r FROM Recomendacao r WHERE r.estilista.IDEstilista = :IDEstilista")
    Page<Recomendacao> getRecomendacaoByEstilista(@Param("IDEstilista") Integer IDEstilista, PageRequest pageRequest);

    @Query("SELECT r FROM Recomendacao r WHERE (r.cliente.IDCliente = :IDCliente AND r.status = 'completed')")
    Page<Recomendacao> getRecomendacaoByCliente(@Param("IDCliente") Integer IDCliente, PageRequest pageRequest);

    @Query("SELECT r FROM Recomendacao r WHERE r.IDRecomendacao = :IDRecomendacao")
    Optional<Recomendacao> getRecomendacaoByID(@Param("IDRecomendacao") Integer IDRecomendacao);
}
