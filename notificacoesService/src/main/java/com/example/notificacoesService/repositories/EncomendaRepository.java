package com.example.notificacoesService.repositories;

import com.example.notificacoesService.model.Encomenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EncomendaRepository extends JpaRepository<Encomenda,Integer> {
}