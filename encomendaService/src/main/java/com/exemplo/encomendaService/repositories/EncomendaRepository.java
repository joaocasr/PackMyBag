package com.exemplo.encomendaService.repositories;

import com.exemplo.encomendaService.model.Encomenda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;


@Repository
public interface EncomendaRepository extends JpaRepository<Encomenda, Integer> {
    
    // //Metodo personalizado para encotnrar encomenda por id da encomenda
    // @Query("SELECT e FROM Encomenda e WHERE e.id = :id")
    // Encomenda getIDEncomenda(@Param("id") int id);
    // Optional<Encomenda> findByIDint id); // ja existe um metodo findById no JpaRepository

    // Procurar por código de encomenda (retorna uma única encomenda)
    Optional<Encomenda> findByCodigoEncomenda(String codigoEncomenda);

    // Procurar por Dataentrega apenas
    List<Encomenda> findByDataEntrega(String dataEntrega);

    // Procurar por DataDevolucao apenas
    List<Encomenda> findByDataDevolucao(String dataDevolucao);

    // Procurar por local de entrega
    List<Encomenda> findByLocalEntrega(String localEntrega);

    // Procurar por status
    List<Encomenda> findByStatus(String status);

    // Metodo personalizado para encontrar encomendas por id do cliente
    @Query("select e FROM Encomenda e where e.cliente.IDCliente = :idcliente")
    List<Encomenda> getEncomendasCliente(@Param("idcliente") Integer idcliente);

    // @Query("SELECT e FROM Encomenda e JOIN e.cliente c JOIN e.loja l WHERE c.IDCliente = :clienteId AND l.IDLoja = :lojaId")
    // List<Encomenda> findEncomendasByClienteIdAndLojaId(@Param("clienteId") int clienteId, @Param("lojaId") int lojaId);

    @Query("SELECT e FROM Encomenda e JOIN e.cliente c WHERE c.IDCliente = :clienteId")
    List<Encomenda> findEncomendasByClienteId(@Param("clienteId") int clienteId);

    @Transactional
    @Modifying
    @Query("UPDATE Encomenda e SET e.status = :novoStatus WHERE e.id = :idEncomenda")
    int updateStatusById(@Param("idEncomenda") int idEncomenda, @Param("novoStatus") String novoStatus);
}

    //Metodo personalizado para encotnrar encontrar todas as encomendas
    // @Query("SELECT e FROM Encomenda e")
    // List<Encomenda> getAllEncomendasRepository();
    // ja existe um metodo findAll no JpaRepository automaticamente

