package com.example.catalogService.repositories;

import com.example.catalogService.model.Loja;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LojaRepository extends JpaRepository<Loja,Integer> {
}
