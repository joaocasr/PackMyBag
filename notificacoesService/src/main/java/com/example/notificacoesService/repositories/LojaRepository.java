package com.example.notificacoesService.repositories;

import com.example.notificacoesService.model.Cliente;
import com.example.notificacoesService.model.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LojaRepository extends JpaRepository<Loja,Integer> {
}
