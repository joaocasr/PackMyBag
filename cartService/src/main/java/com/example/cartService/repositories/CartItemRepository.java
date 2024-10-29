package com.example.cartService.repositories;

import com.example.cartService.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<Item, String> {

    // get item by code
    @Query("SELECT i FROM Item i WHERE i.codigo = :codigo")
    Item getItemByCodigo(@Param("codigo") String codigo);

    // get all cart items for a user
    @Query("SELECT i FROM Item i WHERE i.codigo IN (SELECT c.cart FROM Cliente c WHERE c.username = :username)")
    Page<Item> getCartItemsByUsername(@Param("username") String username, PageRequest pageable);

    // get all cart items for a user as list
    @Query("SELECT i FROM Item i WHERE i.codigo IN (SELECT c.cart FROM Cliente c WHERE c.username = :username)")
    List<Item> getCartItemsByUsername_List(@Param("username") String username);
}
