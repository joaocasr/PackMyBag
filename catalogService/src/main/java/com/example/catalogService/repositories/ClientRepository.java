package com.example.catalogService.repositories;

import com.example.catalogService.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Cliente,String> {

    @Query("select u from Cliente u where u.username = :username")
    Cliente getClienteByUsername(@Param("username") String username);
}
