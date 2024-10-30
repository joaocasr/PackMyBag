package com.example.favoritosService.repositories;

import com.example.favoritosService.model.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LojaRepository extends JpaRepository<Loja, Integer> {
    @Query("select l FROM Loja l where l.identificador = :identificador")
    Optional<Loja> getLojaById(@Param("identificador") int identificador);

}
