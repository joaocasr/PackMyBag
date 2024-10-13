package com.example.catalogService.repositories;

import com.example.catalogService.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,String> {

    @Query("select i FROM Item i where i.genero= :gender")
    Page<Item> getItemsByGender(@Param("gender") String gender, PageRequest pageable);

    @Query("select i FROM Item i where i.preco>= :min AND i.preco<= :max")
    Page<Item> getItemsByPrice(@Param("min") int min, @Param("max") int max, PageRequest pageable);

    @Query("select i FROM Item i where i.preco>= :min AND i.preco<= :max AND i.genero= :gender")
    Page<Item> getItemsByPriceandGender(@Param("min") int min, @Param("max") int max, @Param("gender") String gender, PageRequest pageable);

}
