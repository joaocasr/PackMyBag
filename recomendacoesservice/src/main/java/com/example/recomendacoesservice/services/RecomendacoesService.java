package com.example.recomendacoesservice.services;

import com.example.recomendacoesservice.exceptions.*;
import com.example.recomendacoesservice.repositories.*;
import com.example.recomendacoesservice.model.*;
import com.example.recomendacoesservice.dto.*;
import com.example.recomendacoesservice.mappers.*;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecomendacoesService {
    private clientesRepository clientesRepository;
    private estilistasRepository estilistaRepository;
    private pedidosRepository pedidosRepository;
    private recomendacoesRepository recomendacoesRepository;
    private itemsRepository itemsRepository;
    private mappersRecomendacoes mappersRecomendacoes;

    public RecomendacoesService(clientesRepository cR, estilistasRepository eR, pedidosRepository pR, recomendacoesRepository rR, itemsRepository iR, mappersRecomendacoes mP) {
        clientesRepository = cR;
        estilistaRepository = eR;
        pedidosRepository = pR;
        recomendacoesRepository = rR;
        itemsRepository = iR;
        mappersRecomendacoes = mP;
    }

    private Cliente checkIfClienteExists(Integer id) throws InexistentClientID {
        return clientesRepository.getCliente(id).orElseThrow(InexistentClientID::new);
    }

    private Estilista checkIfEstilistaExists(Integer id) throws InexistentStylistID{
        return estilistaRepository.getEstilista(id).orElseThrow(InexistentStylistID::new);
    }

    private Recomendacao checkIfRecomendacaoExists(Integer id) throws InexistentRecommendationID{
        return recomendacoesRepository.getRecomendacaoByID(id).orElseThrow(InexistentRecommendationID::new);
    }

    public List<pedidoDTO> getPedidosEstilista(int IDEstilista, int page, int number) throws InexistentStylistID, InexistentRequests {
        Estilista est = checkIfEstilistaExists(IDEstilista);
        return estilistaRepository.getPedidosbyEstilista(est.getIDEstilista(), PageRequest.of(page, number)).stream().map(x -> mappersRecomendacoes.pedidoToDTO(x)).collect(Collectors.toList());
    }

    public List<pedidoDTO> getPedidosCliente(int IDCliente, int page, int number) throws InexistentClientID, InexistentRequests {
        Cliente c = checkIfClienteExists(IDCliente);
        return pedidosRepository.getPedidosByCliente(c.getIDCliente(), PageRequest.of(page, number)).stream().map(x -> mappersRecomendacoes.pedidoToDTO(x)).collect(Collectors.toList());
    }

    public List<recomendacaoDTO> getRecomendacoesEstilista(int IDEstilista, int page, int number) throws InexistentStylistID, InexistentRecommendations {
        Estilista est = checkIfEstilistaExists(IDEstilista);
        return recomendacoesRepository.getRecomendacaoByEstilista(est.getIDEstilista(), PageRequest.of(page, number)).stream().map(x -> mappersRecomendacoes.recomendacaoToDTO(x)).collect(Collectors.toList());
    }

    public List<recomendacaoDTO> getRecomendacoesCliente(int IDCliente, int page, int number) throws InexistentClientID, InexistentRecommendations{
        Cliente c = checkIfClienteExists(IDCliente);
        return recomendacoesRepository.getRecomendacaoByCliente(c.getIDCliente(), PageRequest.of(page, number)).stream().map(x -> mappersRecomendacoes.recomendacaoToDTO(x)).collect(Collectors.toList());
    }

    public void newPedido(pedidoToEstilistaDTO pedidoToEstilista) throws InexistentClientID, InexistentStylistID {
        Cliente c = checkIfClienteExists(pedidoToEstilista.getIDCliente());
        Estilista e = checkIfEstilistaExists(pedidoToEstilista.getIDEstilista());

        Pedido np = new Pedido();
        np.setCliente(c);
        np.setCores(pedidoToEstilista.getCores());
        np.setEstilos(pedidoToEstilista.getEstilos());
        np.setOrcamento(pedidoToEstilista.getOrcamento());
        np.setNrOutfits(pedidoToEstilista.getNrOutfits());
        np.setPeçasExcluidas(pedidoToEstilista.getPeçasExcluidas());
        np.setFabricsPreferences(pedidoToEstilista.getFabricsPreferences());
        np.setOccasions(pedidoToEstilista.getOccasions());


        e.addPedido(np);
        pedidosRepository.save(np);
        estilistaRepository.save(e);
    }

    public void newRecomendacao(recomendacaoToClienteDTO recomendacaoToCliente) throws InexistentClientID, InexistentStylistID, CompleteWithoutItems {
        Cliente c = checkIfClienteExists(recomendacaoToCliente.getIDCliente());
        Estilista e = checkIfEstilistaExists(recomendacaoToCliente.getIDEstilista());

        Recomendacao nR = new Recomendacao();
        nR.setCliente(c);
        nR.setEstilista(e);
        nR.setDescricao(recomendacaoToCliente.getDescricao());
        nR.setStatus(recomendacaoToCliente.getStatus());

        if(recomendacaoToCliente.getConjunto().isEmpty() && recomendacaoToCliente.getStatus().equals("completed")){
            throw new CompleteWithoutItems();
        }else if((!nR.getStatus().equals("pending") && !nR.getStatus().equals("completed"))){
            nR.setStatus("pending");
        }else {
            for (itemDTO iDTO : recomendacaoToCliente.getConjunto()) {
                Item iTemp = iDTO.itemDTOtoItem();
                nR.addItem(iTemp);
            }
            c.addRecomendacao(nR);
        }

        recomendacoesRepository.save(nR);
        clientesRepository.save(c);
    }

    public void removeRecomendacao(int IDRecomendacao) throws InexistentRecommendationID, RecommendationAlreadyCompleted {
        Recomendacao r = checkIfRecomendacaoExists(IDRecomendacao);

        if(r.getStatus().equals("completed")) throw new RecommendationAlreadyCompleted("removed");
        else{
            recomendacoesRepository.delete(r);
        }
    }

    public void editRecomendacao(editRecomendacaoDTO editRecomendacaoDTO) throws InexistentRecommendationID, RecommendationAlreadyCompleted, CompleteWithoutItems, NoItems{
        // a parte de remover items poderá ter de ser alterada, visto que estou a assumir que
        // apenas tens um item de cada codigo numa recomendacao
        Recomendacao r = checkIfRecomendacaoExists(editRecomendacaoDTO.getIDRecomendacao());

        if(r.getStatus().equals("completed")) throw new RecommendationAlreadyCompleted("edited");
        else{
            Set<Item> itemsToRemove = new HashSet<>();

            if(!editRecomendacaoDTO.getDescricao().isEmpty()){
                r.setDescricao(editRecomendacaoDTO.getDescricao());
            }
            if(!editRecomendacaoDTO.getConjunto().isEmpty() && !editRecomendacaoDTO.getItemsEditType().isEmpty()){
                if(editRecomendacaoDTO.getItemsEditType().equals("addItems")){
                    for (itemDTO iDTO : editRecomendacaoDTO.getConjunto()) {
                        Item iTemp = iDTO.itemDTOtoItem();
                        r.addItem(iTemp);
                    }
                }else if(editRecomendacaoDTO.getItemsEditType().equals("removeItems")){
                    if(r.getConjunto().isEmpty()){
                        throw new NoItems("items previously");
                    }
                    for(Item i : r.getConjunto()){ // selecionar os items a remover
                        for(itemDTO iDTO : editRecomendacaoDTO.getConjunto()){
                            if(i.getCodigo().equals(iDTO.getCodigo())){
                                itemsToRemove.add(i);
                            }
                        }
                    }
                    for(Item i : itemsToRemove){ //remover os items selecionados
                        r.removeItem(i);
                    }
                }
            }
            if(!editRecomendacaoDTO.getStatus().isEmpty()){
                if(editRecomendacaoDTO.getStatus().equals("pending") || editRecomendacaoDTO.getStatus().equals("completed")){
                    if(editRecomendacaoDTO.getStatus().equals("completed") && r.getConjunto().isEmpty()){
                        throw new CompleteWithoutItems();
                    }else if(editRecomendacaoDTO.getConjunto().isEmpty()){
                        throw new NoItems("items");
                    }else if(editRecomendacaoDTO.getItemsEditType().isEmpty()){
                        throw new NoItems("item edit instruction");
                    }
                    r.setStatus(editRecomendacaoDTO.getStatus());
                }
            }

            recomendacoesRepository.save(r);
            if(!editRecomendacaoDTO.getConjunto().isEmpty() && editRecomendacaoDTO.getItemsEditType().equals("removeItems")){
                itemsRepository.deleteAll(itemsToRemove); // apenas é realizado se itemsEditType = "removeItems"
            }
        }
    }
}
