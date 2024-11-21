package com.example.recomendacoesservice.repositories;

import com.example.recomendacoesservice.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface itemsRepository extends JpaRepository<Item, Integer>{
    @Query("SELECT i FROM Item i WHERE i.IDItem = :IDItem")
    Optional<Item> getItem(@Param("IDItem") Integer IDItem);

    @Query("SELECT i FROM Item i WHERE i.codigo = :codigo")
    Optional<Item> getItemByCodigo(@Param("codigo") String codigo);
}
