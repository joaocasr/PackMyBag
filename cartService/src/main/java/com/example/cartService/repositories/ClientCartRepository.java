package com.example.cartService.repositories;

import com.example.cartService.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientCartRepository extends JpaRepository<Cliente, String>{
    
    // ir buscar o user
    @Query("SELECT c FROM Cliente c WHERE c.username = :username")
    Cliente getClienteByUsername(@Param("username") String username);

    //ir buscar os carrinho do cliente
    @Query("select c.cart FROM Cliente c where c.username = :username")
    Page<Cart> getCartByUsername(@Param("username") String username, PageRequest pageable);

    // ir buscar os carrinho do cliente como um cart
    @Query("select c.cart FROM Cliente c where c.username = :username")
    Cart getCartByUsername_Cart(@Param("username") String username);
}
