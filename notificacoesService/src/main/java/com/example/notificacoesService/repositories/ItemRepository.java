package com.example.notificacoesService.repositories;

import com.example.notificacoesService.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item,Integer> {

    @Query("select i from Item i where i.lojaid= :idloja and i.codigo=:codigo")
    Item getItemShop(@Param("idloja") String idloja, @Param("codigo") String codigo);
}
