package com.exemplo.encomendaService.repositories;

import com.exemplo.encomendaService.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("select u FROM Cliente u where u.username = :username")
    Optional<Cliente> findByUsername(@Param("username") String username);

}