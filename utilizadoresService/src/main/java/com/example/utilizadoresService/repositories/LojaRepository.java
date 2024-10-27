package com.example.utilizadoresService.repositories;

import com.example.utilizadoresService.model.Cliente;
import com.example.utilizadoresService.model.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LojaRepository extends JpaRepository<Loja, Integer> {


    @Query("select l from Loja l where l.nome = :nome")
    Loja getLojaByNome(@Param("nome") String nome);


}
