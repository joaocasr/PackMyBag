package com.example.catalogService.repositories;

import com.example.catalogService.model.Item;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {

    @Query("select i FROM Item i where i.genero= :gender")
    Page<Item> getItemsByGender(@Param("gender") String gender, PageRequest pageable);

    @Query("select i FROM Item i where i.preco>= :min AND i.preco<= :max")
    Page<Item> getItemsByPrice(@Param("min") int min, @Param("max") int max, PageRequest pageable);

    @Query("select i FROM Item i where i.preco>= :min AND i.preco<= :max AND i.genero= :gender")
    Page<Item> getItemsByPriceandGender(@Param("min") int min, @Param("max") int max, @Param("gender") String gender, PageRequest pageable);

    @Query("select i FROM Item i where i.codigo= :code and i.loja.IDLoja= :idLoja")
    Set<Item> getItemsByCodeShop(@Param("code") String code,@Param("idLoja") int idLoja);

    @Query("select i from Item i where i.loja.IDLoja= :idLoja")
    Set<Item> getShopItems(@Param("idLoja") int idLoja);

//    @Query("update Peca p set p.sets = :conjuntos where p.codigo= :codigo and p.loja.IDLoja= :idLoja")
//    void updatePeca(@Param("codigo") String codigo, @Param("idLoja") int idLoja, @Param("conjuntos") Set<com.example.catalogService.model.Set> conjuntos);

}
