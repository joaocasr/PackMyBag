package com.example.cartService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.cartService.model.ItemEncomenda;

@Repository
public interface ItemEncomendaRepository extends JpaRepository<ItemEncomenda,Integer> {
    
    @Query("SELECT i FROM ItemEncomenda i WHERE i.codigo = :codigo AND i.idLoja = :idloja")
    ItemEncomenda getItemByIdCode(@Param("codigo") String codigo, @Param("idloja") int idloja);

}
