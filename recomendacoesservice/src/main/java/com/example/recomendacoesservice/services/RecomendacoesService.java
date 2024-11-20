package com.example.recomendacoesservice.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.recomendacoesservice.dto.editPedidoDTO;
import com.example.recomendacoesservice.dto.itemDTO;
import com.example.recomendacoesservice.dto.pedidoDTO;
import com.example.recomendacoesservice.dto.pedidoToEstilistaDTO;
import com.example.recomendacoesservice.dto.recomendacaoToClienteDTO;
import com.example.recomendacoesservice.exceptions.CompleteWithoutItemsOrDescription;
import com.example.recomendacoesservice.exceptions.InexistentClientUsername;
import com.example.recomendacoesservice.exceptions.InexistentRequestID;
import com.example.recomendacoesservice.exceptions.InexistentRequests;
import com.example.recomendacoesservice.exceptions.InexistentStylistUsername;
import com.example.recomendacoesservice.exceptions.NoItems;
import com.example.recomendacoesservice.exceptions.RequestAlreadyCompleted;
import com.example.recomendacoesservice.exceptions.UnknownEditType;
import com.example.recomendacoesservice.mappers.mappersRecomendacoes;
import com.example.recomendacoesservice.model.Cliente;
import com.example.recomendacoesservice.model.Estilista;
import com.example.recomendacoesservice.model.Item;
import com.example.recomendacoesservice.model.Pedido;
import com.example.recomendacoesservice.repositories.clientesRepository;
import com.example.recomendacoesservice.repositories.estilistasRepository;
import com.example.recomendacoesservice.repositories.itemsRepository;
import com.example.recomendacoesservice.repositories.pedidosRepository;

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
        Optional<Cliente> c =  clientesRepository.getCliente(username);
        if(c.isPresent()) return c.get();
        else throw new InexistentClientUsername();
    }

    private Estilista getEstilistaIfExists(String username) throws InexistentStylistUsername {
        Optional<Estilista> e =  estilistaRepository.getEstilista(username);
        if(e.isPresent()) return e.get();
        else throw new InexistentStylistUsername();
    }

    private Cliente createClienteIfDoesntExist(String username){
        Optional<Cliente> auxOC = clientesRepository.getCliente(username);
        if(auxOC.isPresent()){
            return auxOC.get();
        }else{
            Cliente c = new Cliente(username);
            clientesRepository.save(c);
            return c;
        }
    }

    private Estilista createEstilistaIfDoesntExist(String username){
        Optional<Estilista> auxOE = estilistaRepository.getEstilista(username);
        if(auxOE.isPresent()){
            return auxOE.get();
        }else{
            Estilista c = new Estilista(username);
            estilistaRepository.save(c);
            return c;
        }
    }

    private Pedido checkIfPedidoExists(Integer id) throws InexistentRequestID{
        return pedidosRepository.getPedido(id).orElseThrow(InexistentRequestID::new);
    }

    public List<pedidoDTO> getPedidosEstilista(String username, int page, int number) throws InexistentStylistUsername, InexistentRequests {
        Estilista est = getEstilistaIfExists(username);


        Page<Pedido> pedidosPage = estilistaRepository.getPedidosbyEstilista(est.getUsername(), PageRequest.of(page, number));

        List<pedidoDTO> pedidoDTOList = pedidosPage.stream().map(mappersRecomendacoes::pedidoToDTO).toList();

        return pedidoDTOList;
    }

    public List<recomendacaoToClienteDTO> getPedidosCliente(String username, int page, int number) throws InexistentClientUsername, InexistentRequests {
        Cliente c = getClienteIfExists(username);
        return pedidosRepository.getPedidosByCliente(c.getIDCliente(), PageRequest.of(page, number)).stream().map(x -> new recomendacaoToClienteDTO(x)).collect(Collectors.toList());
    }

    public void newPedido(pedidoToEstilistaDTO pedidoToEstilista) throws InexistentClientUsername, InexistentStylistUsername {
        Cliente c = createClienteIfDoesntExist(pedidoToEstilista.getUsernameCliente());
        Estilista e = createEstilistaIfDoesntExist(pedidoToEstilista.getUsernameEstilista());

    
        Pedido np = new Pedido();
        np.setCliente(c);
        np.setCores(pedidoToEstilista.getCores());
        np.setEstilos(pedidoToEstilista.getEstilos());
        np.setOrcamento(pedidoToEstilista.getOrcamento());
        np.setNrOutfits(pedidoToEstilista.getNrOutfits());
        np.setPeçasExcluidas(pedidoToEstilista.getPeçasExcluidas());
        np.setFabricsPreferences(pedidoToEstilista.getFabricsPreferences());
        np.setOccasions(pedidoToEstilista.getOccasions());
        np.setStatus("pending");


        e.addPedido(np);
        pedidosRepository.save(np);
        estilistaRepository.save(e);
    }

    public void editPedido(editPedidoDTO editPedidoDTO) throws InexistentRequestID, RequestAlreadyCompleted, CompleteWithoutItemsOrDescription, NoItems, UnknownEditType{
        // estou a assumir que apenas tens um item de cada codigo numa recomendacao
        Pedido p = checkIfPedidoExists(editPedidoDTO.getIDPedido());

        if(p.getStatus().equals("completed")) throw new RequestAlreadyCompleted("edited");
        else {
            Set<Item> itemsToRemove = new HashSet<>();

            if (!editPedidoDTO.getDescricao().isEmpty()) {
                p.setDescricao(editPedidoDTO.getDescricao());
            }
            if (!editPedidoDTO.getConjunto().isEmpty()) {
                if (!editPedidoDTO.getItemsEditType().isEmpty()) {
                    if (editPedidoDTO.getItemsEditType().equals("add")) {
                        for (itemDTO iDTO : editPedidoDTO.getConjunto()) {
                            Item iTemp = iDTO.itemDTOtoItem();
                            p.addItem(iTemp);
                        }
                    } else if (editPedidoDTO.getItemsEditType().equals("remove")) {
                        if (p.getConjunto().isEmpty()) {
                            throw new NoItems("items previously");
                        }
                        for (Item i : p.getConjunto()) { // selecionar os items a remover
                            for (itemDTO iDTO : editPedidoDTO.getConjunto()) {
                                if (i.getCodigo().equals(iDTO.getCodigo())) {
                                    itemsToRemove.add(i);
                                }
                            }
                        }
                        for (Item i : itemsToRemove) { //remover os items selecionados
                            p.removeItem(i);
                        }
                    }else{
                        throw new UnknownEditType();
                    }
                }else{
                    throw new NoItems("item edit instruction");
                }
            }else{
                throw new NoItems("items");
            }

            if(!editPedidoDTO.getStatus().isEmpty()){
                if(editPedidoDTO.getStatus().equals("pending") || editPedidoDTO.getStatus().equals("completed")){
                    if(editPedidoDTO.getStatus().equals("completed") && p.getConjunto().isEmpty() && p.getDescricao().isEmpty()){
                        throw new CompleteWithoutItemsOrDescription();
                    }
                    p.setStatus(editPedidoDTO.getStatus());
                }
            }

            pedidosRepository.save(p);
            if(!editPedidoDTO.getConjunto().isEmpty() && editPedidoDTO.getItemsEditType().equals("remove")){
                itemsRepository.deleteAll(itemsToRemove); // apenas é realizado se itemsEditType = "remove"
            }
        }
    }
}
