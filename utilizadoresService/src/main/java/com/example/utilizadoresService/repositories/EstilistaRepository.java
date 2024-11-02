package com.example.utilizadoresService.repositories;

import com.example.utilizadoresService.model.Estilista;
import com.example.utilizadoresService.model.NormalCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EstilistaRepository extends JpaRepository<Estilista, Integer> {


    @Query("select c from Estilista c where c.username = :username")
    Estilista getClienteByUsername(@Param("username") String username);


}
