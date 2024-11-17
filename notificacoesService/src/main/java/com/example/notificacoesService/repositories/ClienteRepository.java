package com.example.notificacoesService.repositories;

import com.example.notificacoesService.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

    @Query("select c from Cliente c where c.username=:username")
    Optional<Cliente> getClienteByUsername(@Param("username") String username);


}
