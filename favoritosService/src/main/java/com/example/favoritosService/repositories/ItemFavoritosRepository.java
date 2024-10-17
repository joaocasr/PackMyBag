package com.example.favoritosService.repositories;

import com.example.favoritosService.model.Cliente;
import com.example.favoritosService.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ItemFavoritosRepository extends JpaRepository<Item,String> {

    // ir buscar item
    @Query("select i FROM Item i where i.codigo = :code")
    Item getItemByCode(@Param("code") String code);

}

