package com.example.notificacoesService.repositories;

import com.example.notificacoesService.model.Encomenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface EncomendaRepository extends JpaRepository<Encomenda,Integer> {

    @Query("select c from Encomenda c where c.codigoEncomenda=:codigoEncomenda")
    Optional<Encomenda> getEncomendabyCode(@Param("codigoEncomenda") String codigoEncomenda);
}