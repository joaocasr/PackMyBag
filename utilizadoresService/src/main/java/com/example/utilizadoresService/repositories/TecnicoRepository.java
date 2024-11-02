package com.example.utilizadoresService.repositories;

import com.example.utilizadoresService.model.NormalCliente;
import com.example.utilizadoresService.model.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {


    @Query("select c from Tecnico c where c.username = :username")
    Tecnico getClienteByUsername(@Param("username") String username);


}