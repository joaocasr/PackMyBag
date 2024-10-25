package com.exemplo.encomendaService.repositories;

import com.exemplo.encomendaService.model.Encomenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.time.LocalDate;


@Repository
public interface EncomendaRepository extends JpaRepository<Encomenda, Integer> {
    
    @Query("SELECT e FROM Encomenda e WHERE e.cliente.email = :email AND e.dataEntrega = :dataEntrega")
    List<Encomenda> findEncomendaByEmailAndData(@Param("email") String email, @Param("dataEntrega") String dataEntrega);

    // Método personalizado para encontrar encomendas por cliente
    //SELECT * FROM Encomenda WHERE cliente_id = ?;
    // List<Encomenda> findByCliente_Id(Integer clienteId);

    //SELECT * FROM Encomenda WHERE status = ?;
    List<Encomenda> findByStatus(String status);

    //SELECT * FROM Encomenda WHERE status = ? AND local_entrega = ?;
    List<Encomenda> findByStatusAndLocalEntrega(String status, String localEntrega);

    //SELECT * FROM Encomenda WHERE local_entrega = ?;
    List<Encomenda> findByLocalEntrega(String localEntrega);

    // Outros métodos de consulta

    // Coloco em baixo os metodos definos automaticamente pelo JPA

    //public List<Encomenda> findAll();

    // public Optional<Encomenda> findById(int id);

    // public Encomenda save(Encomenda encomenda);

    // public void deleteById(int id);

    // public void delete(Encomenda encomenda);

    // public boolean existsById(int id);

    // public long count();

    // public void deleteAll();

    // public void deleteAll(Iterable<? extends Encomenda> encomendas);

    // public List<Encomenda> saveAll(Iterable<Encomenda> encomendas);

    // public List<Encomenda> findAllById(Iterable<Integer> ids);

    // public void flush();

    // public void deleteInBatch(Iterable<Encomenda> encomendas);

    // public void deleteAllInBatch();

    // public Encomenda getOne(Integer id);

    // public Encomenda saveAndFlush(Encomenda encomenda);

    // public void deleteAllByIdInBatch(Iterable<Integer> ids);

    // public void deleteAllInBatch(Iterable<Encomenda> encomendas);
}


/*
 
CRUD (Criar, Ler, Atualizar e Excluir) básicas, sem que você precise implementá-los explicitamente. Aqui estão alguns exemplos de métodos que já vêm prontos com o JpaRepository:

Métodos CRUD já disponíveis no JpaRepository:
save(S entity): Salva uma entidade. Se ela já existir, faz o update.
saveAll(Iterable<S> entities): Salva uma lista de entidades.
findById(ID id): Busca uma entidade pelo ID (retorna um Optional).
findAll(): Retorna todas as entidades.
findAllById(Iterable<ID> ids): Retorna uma lista de entidades correspondentes a uma lista de IDs.
deleteById(ID id): Exclui uma entidade pelo ID.
delete(S entity): Exclui uma entidade.
deleteAll(): Exclui todas as entidades.
existsById(ID id): Verifica se uma entidade existe pelo ID.
count(): Retorna o número total de entidades.
 * 
 */