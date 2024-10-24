package com.example.notificacoesService.repositories;

import com.example.notificacoesService.model.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacaoRepository extends JpaRepository<Notificacao,Integer> {
}
