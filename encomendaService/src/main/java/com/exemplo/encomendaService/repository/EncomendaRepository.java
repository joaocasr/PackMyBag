package main.java.com.exemplo.encomendaService.repository;

import com.exemplo.encomenda.model.Encomenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncomendaRepository extends JpaRepository<Encomenda, Integer> {
    
    @Query("SELECT e FROM Encomenda e WHERE e.cliente.email = :email AND e.dataEntrega = :dataEntrega")
    List<Encomenda> findEncomendaByEmailAndData(@Param("email") String email, @Param("dataEntrega") LocalDate dataEntrega);

    // Outros métodos de consulta
}
