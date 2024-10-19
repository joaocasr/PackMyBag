package com.example.catalogService.repositories;

import com.example.catalogService.model.Item;
import com.example.catalogService.model.Review;
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

    @Query("select i FROM Item i where i.tipo= :tipo")
    Page<Item> getItemsByType(@Param("tipo") String tipo, PageRequest pageable);

    @Query("select i FROM Item i where i.preco>= :min AND i.preco<= :max")
    Page<Item> getItemsByPrice(@Param("min") int min, @Param("max") int max, PageRequest pageable);

    @Query("select i FROM Item i where i.preco>= :min AND i.preco<= :max AND i.tipo= :tipo")
    Page<Item> getItemsByPriceandType(@Param("min") int min, @Param("max") int max, @Param("tipo") String tipo, PageRequest pageable);

    @Query("select i FROM Item i where i.codigo= :code and i.loja.IDLoja= :idLoja")
    Set<Item> getItemsByCodeShop(@Param("code") String code,@Param("idLoja") int idLoja);

    @Query("select i from Item i where i.loja.IDLoja= :idLoja")
    Page<Item> getShopItems(@Param("idLoja") int idLoja, PageRequest pageable);

    @Query("select i from Item i where i.codigo= :codigo")
    Item getItemByCode(@Param("codigo") String codigo);

    @Query("select i.criticas from Item i where i.IDItem = :id")
    Page<Review> getReviews(@Param("id") int id, PageRequest pageable);

}
