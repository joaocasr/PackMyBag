package com.example.notificacoesService.repositories;

import com.example.notificacoesService.model.Notificacao;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Query;

public interface NotificacaoRepository extends JpaRepository<Notificacao,Integer> {

    @Query("SELECT n from Notificacao n where n.cliente.username = :username")
    Page<Notificacao> getNotificationsUser(@Param("username") String username, PageRequest pageable);

}
