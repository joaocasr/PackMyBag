package com.example.utilizadoresService.repositories;

import com.example.utilizadoresService.model.Cliente;
import com.example.utilizadoresService.model.NormalCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NormalClienteRepository extends JpaRepository <NormalCliente, Integer> {


    @Query("select c from NormalCliente c where c.username = :username")
    NormalCliente getClienteByUsername(@Param("username") String username);


}
