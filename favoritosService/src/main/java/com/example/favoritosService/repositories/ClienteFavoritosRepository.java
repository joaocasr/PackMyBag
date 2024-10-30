package com.example.favoritosService.repositories;

import com.example.favoritosService.model.Cliente;
import com.example.favoritosService.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ClienteFavoritosRepository extends JpaRepository<Cliente,String> {

    // ir buscar o user
    @Query("select c FROM Cliente c where c.username = :username")
    Optional<Cliente> getClienteByUsername(@Param("username") String username);

    // ir buscar favoritos do user
    @Query("select c.favoritos FROM Cliente c where c.username = :username")
    Page<Item> getFavItemsByUsername(@Param("username") String username, PageRequest pageable);

    // ir buscar favoritos do user
    @Query("select c.favoritos FROM Cliente c where c.username = :username")
    Set<Item> getFavItemsByUsername_Set(@Param("username") String username);


    /*

    // filtrar favoritos por preço
    @Query("select c.favoritos as f FROM Cliente c where f.preco>= :min AND f.preco<= :max")
    Page<Item> getFavItemsByPrice(@Param("min") int min, @Param("max") int max, PageRequest pageable);

    // filtrar fav por genero
    @Query("select c.favoritos as f FROM Cliente c where f.genero= :gender")
    Page<Item> getFavItemsByGender(@Param("gender") String gender, PageRequest pageable);

    // filtrar fav por tamanho
    @Query("select c.favoritos as f FROM Cliente c where f.tamanho= :tamanho")
    Page<Item> getFavItemsBySize(@Param("tamanho") String tamanho, PageRequest pageable);

    // filtrar fav por genero e preço
    @Query("select c.favoritos as f FROM Cliente c where f.preco>= :min AND f.preco<= :max AND f.genero= :gender")
    Page<Item> getFavItemsByPriceandGender(@Param("min") int min, @Param("max") int max, @Param("gender") String gender, PageRequest pageable);

    // filtrar fav por genero e tamanho
    @Query("select c.favoritos as f FROM Cliente c where f.tamanho= :tamanho AND f.genero= :gender")
    Page<Item> getFavItemsBySizeandGender(@Param("tamanho") String tamanho, @Param("gender") String gender, PageRequest pageable);

    // filtrar fav por tamanho e preço
    @Query("select c.favoritos as f FROM Cliente c where f.preco>= :min AND f.preco<= :max AND f.tamanho= :tamanho")
    Page<Item> getFavItemsByPriceandSize(@Param("min") int min, @Param("max") int max, @Param("tamanho") String gender, PageRequest pageable);

    // usar todos os filtros para fav
    @Query("select c.favoritos as f FROM Cliente c where f.preco>= :min AND f.preco<= :max AND f.tamanho= :tamanho AND f.genero= :gender")
    Page<Item> getFavItemsByPriceSizeGender(@Param("min") int min, @Param("max") int max, @Param("tamanho") String tamanho, @Param("gender") String gender, PageRequest pageable);

    @Query("select c.favoritos as f FROM Cliente c where f.codigo= :code AND c.username= :username")
    Set<Item> getFavItemsByCode(@Param("username") String username, @Param("code") String code);

    */
}
