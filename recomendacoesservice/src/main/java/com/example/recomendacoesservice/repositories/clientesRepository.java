package com.example.recomendacoesservice.repositories;

import com.example.recomendacoesservice.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface clientesRepository extends JpaRepository<Cliente, String>{
    @Query("select c FROM Cliente c where c.username = :username")
    Optional<Cliente> getCliente(@Param("username") String username);
}
