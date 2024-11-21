package com.example.recomendacoesservice.services;

import com.example.recomendacoesservice.dto.pedidoDTO;
import com.example.recomendacoesservice.dto.pedidoToEstilistaDTO;
import com.example.recomendacoesservice.dto.recomendacaoToClienteDTO;
import com.example.recomendacoesservice.dto.statusPedidoDTO;
import com.example.recomendacoesservice.dto.editPedidoDTO;
import com.example.recomendacoesservice.dto.addRemoveItemDTO;
import com.example.recomendacoesservice.dto.pedidoInfoDTO;
import com.example.recomendacoesservice.exceptions.InexistentClientUsername;
import com.example.recomendacoesservice.exceptions.InexistentRequestName;
import com.example.recomendacoesservice.exceptions.InexistentRequests;
import com.example.recomendacoesservice.exceptions.InexistentStylistUsername;
import com.example.recomendacoesservice.exceptions.EmptyNameEstilistaCliente;
import com.example.recomendacoesservice.exceptions.RequestCompletedPendingPayed;
import com.example.recomendacoesservice.exceptions.IllegalStatus;
import com.example.recomendacoesservice.exceptions.ItemNotAdded;
import com.example.recomendacoesservice.exceptions.EmptyDTO;
import com.example.recomendacoesservice.exceptions.NoItems;
import com.example.recomendacoesservice.exceptions.ItemAlreadyAdded;
import com.example.recomendacoesservice.exceptions.RequestNameAlreadyExists;
import com.example.recomendacoesservice.model.Cliente;
import com.example.recomendacoesservice.model.Estilista;
import com.example.recomendacoesservice.model.Pedido;
import com.example.recomendacoesservice.model.Item;
import com.example.recomendacoesservice.repositories.clientesRepository;
import com.example.recomendacoesservice.repositories.estilistasRepository;
import com.example.recomendacoesservice.repositories.pedidosRepository;
import com.example.recomendacoesservice.repositories.itemsRepository;
import com.example.recomendacoesservice.mappers.mappersRecomendacoes;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class RecomendacoesService {
    private clientesRepository clientesRepository;
    private estilistasRepository estilistaRepository;
    private pedidosRepository pedidosRepository;
    private itemsRepository itemsRepository;
    private mappersRecomendacoes mappersRecomendacoes;

    public RecomendacoesService(clientesRepository cR, estilistasRepository eR, pedidosRepository pR, itemsRepository iR, mappersRecomendacoes mP) {
        clientesRepository = cR;
        estilistaRepository = eR;
        pedidosRepository = pR;
        itemsRepository = iR;
        mappersRecomendacoes = mP;
    }

    private Cliente getClienteIfExists(String username) throws InexistentClientUsername {
        Optional<Cliente> c = clientesRepository.getCliente(username);
        if(c.isPresent()) {
            return c.get();
        }else{
            throw new InexistentClientUsername();
        }
    }

    private Estilista getEstilistaIfExists(String username) throws InexistentStylistUsername {
        Optional<Estilista> e = estilistaRepository.getEstilista(username);
        if(e.isPresent()) {
            return e.get();
        }else{
            throw new InexistentStylistUsername();
        }
    }

    private Cliente createClienteIfDoesntExist(String username){
        Optional<Cliente> auxOC = clientesRepository.getCliente(username);
        if(auxOC.isPresent()){
            return auxOC.get();
        }else{
            Cliente c = new Cliente();
            c.setUsername(username);
            clientesRepository.save(c);
            return c;
        }
    }

    private Estilista createEstilistaIfDoesntExist(String username){
        Optional<Estilista> auxOE = estilistaRepository.getEstilista(username);
        if(auxOE.isPresent()){
            return auxOE.get();
        }else{
            Estilista c = new Estilista();
            c.setUsername(username);
            estilistaRepository.save(c);
            return c;
        }
    }

    private Pedido getPedidoIfExists(String nome) throws InexistentRequestName {
        Optional<Pedido> p = pedidosRepository.getPedido(nome);
        if(p.isPresent()) {
            return p.get();
        }else{
            throw new InexistentRequestName();
        }
    }

    private Boolean checkIfNomePedidoAlreadyExists(String nome){
        Optional<Pedido> p = pedidosRepository.getPedido(nome);
        return p.isPresent();
    }

    public List<pedidoDTO> getPedidosEstilista(String username, int page, int number) throws InexistentStylistUsername, InexistentRequests {
        Estilista est = getEstilistaIfExists(username);
        return estilistaRepository.getPedidosbyEstilista(est.getUsername(), PageRequest.of(page, number)).stream().filter(x -> x.getStatus().equalsIgnoreCase("PAYED")).map(x -> mappersRecomendacoes.pedidoToDTO(x)).collect(Collectors.toList());
    }

    public List<pedidoInfoDTO> getPedidosInfoEstilista(String username) throws InexistentStylistUsername, InexistentRequests{
        Estilista est = getEstilistaIfExists(username);
        return estilistaRepository.getPedidosbyEstilistaList(est.getUsername()).stream().filter(x -> x.getStatus().equalsIgnoreCase("PAYED")).map(x -> mappersRecomendacoes.pedidoToInfoDTO(x)).collect(Collectors.toList());
    }

    public List<recomendacaoToClienteDTO> getPedidosCliente(String username, int page, int number) throws InexistentClientUsername, InexistentRequests {
        Cliente c = getClienteIfExists(username);
        return pedidosRepository.getPedidosByCliente(c.getIDCliente(), PageRequest.of(page, number)).stream().map(x -> new recomendacaoToClienteDTO(x)).collect(Collectors.toList());
    }

    public void newPedido(pedidoToEstilistaDTO pedidoToEstilista) throws InexistentClientUsername, InexistentStylistUsername, EmptyNameEstilistaCliente, RequestNameAlreadyExists {
        if(pedidoToEstilista.getNome().isEmpty() || pedidoToEstilista.getUsernameEstilista().isEmpty() || pedidoToEstilista.getUsernameCliente().isEmpty() || pedidoToEstilista.getNome().isBlank() || pedidoToEstilista.getUsernameEstilista().isBlank() || pedidoToEstilista.getUsernameCliente().isBlank()){
            throw new EmptyNameEstilistaCliente();
        }

        Cliente c = createClienteIfDoesntExist(pedidoToEstilista.getUsernameCliente());
        Estilista e = createEstilistaIfDoesntExist(pedidoToEstilista.getUsernameEstilista());

        if(checkIfNomePedidoAlreadyExists(pedidoToEstilista.getNome())){
            throw new RequestNameAlreadyExists();
        }

        Pedido np = new Pedido();
        np.setCliente(c);
        np.setNome(pedidoToEstilista.getNome());
        np.setCores(pedidoToEstilista.getCores());
        np.setEstilos(pedidoToEstilista.getEstilos());
        np.setOrcamento(pedidoToEstilista.getOrcamento());
        np.setNrOutfits(pedidoToEstilista.getNrOutfits());
        np.setPeçasExcluidas(pedidoToEstilista.getPeçasExcluidas());
        np.setFabricsPreferences(pedidoToEstilista.getFabricsPreferences());
        np.setOccasions(pedidoToEstilista.getOccasions());
        np.setStatus("PENDING");
        np.setDescricao("");
        np.setConjunto(new HashSet<>());


        e.addPedido(np);
        pedidosRepository.save(np);
        estilistaRepository.save(e);
    }

    public void removePedido(String nome) throws InexistentRequestName, RequestCompletedPendingPayed {
        Pedido p = getPedidoIfExists(nome);
        if(p.getStatus().equalsIgnoreCase("pending")){
            pedidosRepository.delete(p);
        }else{
            throw new RequestCompletedPendingPayed(p.getStatus().toUpperCase(), "removed");
        }
    }

    public void changeStatusPedido(statusPedidoDTO statusPedido) throws InexistentRequestName, IllegalStatus, NoItems, RequestCompletedPendingPayed, EmptyDTO {
        if(statusPedido.getStatus().isEmpty() || statusPedido.getStatus().isBlank()){
            throw new EmptyDTO();
        }
        Pedido p = getPedidoIfExists(statusPedido.getNome());
        if(p.getStatus().equalsIgnoreCase("pending")){
            if(statusPedido.getStatus().equalsIgnoreCase("PAYED")){
                p.setStatus(statusPedido.getStatus().toUpperCase());
                pedidosRepository.save(p);
            }else if(statusPedido.getStatus().equalsIgnoreCase("completed")){
                throw new IllegalStatus("change");
            }else if(!statusPedido.getStatus().equalsIgnoreCase("pending")){
                throw new IllegalStatus("name");
            }
        }else if(p.getStatus().equalsIgnoreCase("PAYED")) {
            if(statusPedido.getStatus().equalsIgnoreCase("completed")){
                if(!(p.getConjunto().isEmpty() || p.getDescricao().isEmpty() || p.getDescricao().isBlank())){
                    p.setStatus(statusPedido.getStatus().toUpperCase());
                    pedidosRepository.save(p);
                }else{
                    throw new NoItems("items or description.");
                }
            }else if(statusPedido.getStatus().equalsIgnoreCase("pending")){
                throw new IllegalStatus("change");
            }else if(!statusPedido.getStatus().equalsIgnoreCase("PAYED")){
                throw new IllegalStatus("name");
            }
        }else{
            throw new RequestCompletedPendingPayed(p.getStatus(), "changed in status");
        }
    }

    public void addItemPedido(addRemoveItemDTO addItem) throws InexistentRequestName, ItemAlreadyAdded, RequestCompletedPendingPayed, EmptyDTO {
        if(addItem.getItem() == null){
            throw new EmptyDTO();
        }

        Pedido p = getPedidoIfExists(addItem.getNome());
        if(p.getStatus().equalsIgnoreCase("PAYED")){
            if(p.containsItemDTO(addItem.getItem())){
                throw new ItemAlreadyAdded();
            }else{
                Item auxI = addItem.getItem().itemDTOtoItem();
                p.addItem(auxI);
                pedidosRepository.save(p);
                //itemsRepository.save(auxI);
            }
        }else{
            throw new RequestCompletedPendingPayed(p.getStatus(), "have items!");
        }
    }

    public void removeItemPedido(addRemoveItemDTO removeItem) throws InexistentRequestName, ItemNotAdded, RequestCompletedPendingPayed, EmptyDTO {
        if(removeItem.getItem() == null){
            throw new EmptyDTO();
        }

        Pedido p = getPedidoIfExists(removeItem.getNome());
        if(p.getStatus().equalsIgnoreCase("PAYED")){
            if(p.containsItemDTO(removeItem.getItem())){
                Item auxI = p.getItemByDTO(removeItem.getItem());
                p.removeItem(auxI);
                pedidosRepository.save(p);
                itemsRepository.delete(auxI);
            }else{
                throw new ItemNotAdded();
            }
        }else{
            throw new RequestCompletedPendingPayed(p.getStatus(), "have items removed!");
        }
    }

    public void editDescricaoOrCompletePedido(editPedidoDTO editPedidoDTO) throws InexistentRequestName, EmptyDTO, RequestCompletedPendingPayed, NoItems, IllegalStatus {
        if((editPedidoDTO.getDescricao().isEmpty() || editPedidoDTO.getDescricao().isBlank()) && (editPedidoDTO.getStatus().isEmpty() || editPedidoDTO.getStatus().isBlank())){
            throw new EmptyDTO();
        }else if(editPedidoDTO.getDescricao().isEmpty() || editPedidoDTO.getDescricao().isBlank()){
            Pedido p = getPedidoIfExists(editPedidoDTO.getNome());
            changeStatusPedido(new statusPedidoDTO(editPedidoDTO.getNome(), editPedidoDTO.getStatus()));
        }else if(editPedidoDTO.getStatus().isEmpty() || editPedidoDTO.getStatus().isBlank()){
            Pedido p = getPedidoIfExists(editPedidoDTO.getNome());
            p.setDescricao(editPedidoDTO.getDescricao());
            pedidosRepository.save(p);
        }else{
            Pedido p = getPedidoIfExists(editPedidoDTO.getNome());
            p.setDescricao(editPedidoDTO.getDescricao());
            pedidosRepository.save(p);
            changeStatusPedido(new statusPedidoDTO(editPedidoDTO.getNome(), editPedidoDTO.getStatus()));
        }
    }
}
