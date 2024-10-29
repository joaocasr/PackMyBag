package com.exemplo.encomendaService.repositories;

import com.exemplo.encomendaService.model.Loja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Integer> {

    @Query("SELECT l FROM Loja l JOIN l.encomendas e WHERE e.IDEncomenda = :encomendaId")
    Loja findLojaByEncomendaId(@Param("encomendaId") int encomendaId);

    @Query("SELECT l FROM Loja l LEFT JOIN FETCH l.encomendas WHERE l.IDLoja = :lojaId")
    Optional<Loja> findLojaWithEncomendas(@Param("lojaId") int lojaId);
}