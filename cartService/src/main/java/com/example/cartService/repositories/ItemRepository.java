package com.example.cartService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.cartService.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
    
    @Query("SELECT i FROM Item i WHERE i.codigo = :codigo AND i.idLoja = :idloja")
    Item getItemByIdCode(@Param("codigo") String codigo, @Param("idloja") int idloja);

}
