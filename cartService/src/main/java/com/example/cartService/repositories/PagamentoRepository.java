package com.example.cartService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.cartService.model.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>{
    @Query("SELECT p FROM Pagamento p JOIN FETCH p.itemEncomendas WHERE p.codigo = :codigo")
    Pagamento findByCode(@Param("codigo") String codigo);

    @Query("SELECT p FROM Pagamento p WHERE p.codigo = :codigo")
    Pagamento findByCodeForm(@Param("codigo") String codigo);

}