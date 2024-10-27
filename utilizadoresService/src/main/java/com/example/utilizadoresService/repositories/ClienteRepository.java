package com.example.utilizadoresService.repositories;

import com.example.utilizadoresService.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository <Cliente, Integer> {


    @Query("select c from Cliente c where c.username = :username")
    Cliente getClienteByUsername(@Param("username") String username);
}
