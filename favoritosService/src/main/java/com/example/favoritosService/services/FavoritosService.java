package com.example.favoritosService.services;

import com.example.favoritosService.dto.FavoritoItemDTO;
import com.example.favoritosService.dto.FavoritoItemInsertDTO;
import com.example.favoritosService.dto.FavoritoItemRemoveDTO;
import com.example.favoritosService.exceptions.*;
import com.example.favoritosService.model.Cliente;
import com.example.favoritosService.model.Item;
import com.example.favoritosService.mappers.FavoritoItemMapper;
import com.example.favoritosService.model.Loja;
import com.example.favoritosService.repositories.ClienteFavoritosRepository;
import com.example.favoritosService.repositories.LojaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FavoritosService {

    private FavoritoItemMapper favoritoItemMapper;
    private ClienteFavoritosRepository clienteFavoritosRepository;
    private LojaRepository lojaRepository;

    public FavoritosService(ClienteFavoritosRepository clienteFavoritosRepository, LojaRepository lojaRepository,FavoritoItemMapper favoritoItemMapper){
        this.favoritoItemMapper = favoritoItemMapper;
        this.clienteFavoritosRepository = clienteFavoritosRepository;
        this.lojaRepository = lojaRepository;
    }

    private Optional<Cliente> checkIfClientExists(String username){
        return clienteFavoritosRepository.getClienteByUsername(username);
    }

    private Optional<Loja> optionalLoja(int id){
        return lojaRepository.getLojaById(id);
    }

    public List<FavoritoItemDTO> getUserFavourites(String username, int page, int number) throws NoCatalogItemsException {
        return clienteFavoritosRepository.getFavItemsByUsername(username, PageRequest.of(page, number)).stream().map(x->favoritoItemMapper.toFavoritoItemDTO(x)).collect(Collectors.toList());
    }

    public List<FavoritoItemDTO> getPerGenderItems(String username, String tipo, int page, int number) throws NoCatalogItemsGenderException {
        List<Item> res = new ArrayList<>();
        //Set<Item> favoritos = clienteFavoritosRepository.getFavItemsByUsername_Set(username);
        Page<Item> favoritos = clienteFavoritosRepository.getFavItemsByUsername(username,PageRequest.of(page, number));
        for(Item i : favoritos){
            if(i.getTipo().equals(tipo)){
                res.add(i);
            }
        }
        return res.stream().map(x->favoritoItemMapper.toFavoritoItemDTO(x)).collect(Collectors.toList());
    }

    public List<FavoritoItemDTO> getPerPriceItems(String username, int min, int max, int page, int number) throws NoCatalogItemsPriceException {
        List<Item> res = new ArrayList<>();
        //Set<Item> favoritos = clienteFavoritosRepository.getFavItemsByUsername_Set(username);
        Page<Item> favoritos = clienteFavoritosRepository.getFavItemsByUsername(username, PageRequest.of(page, number));
        for(Item i : favoritos){
            if(min <= i.getPreco() && i.getPreco() <= max){
                res.add(i);
            }
        }
        return res.stream().map(x->favoritoItemMapper.toFavoritoItemDTO(x)).collect(Collectors.toList());
    }

    public List<FavoritoItemDTO> getPerSizeItems(String username, String dimensoes, int page, int number) throws NoCatalogItemsPriceException {
        List<Item> res = new ArrayList<>();
        //Set<Item> favoritos = clienteFavoritosRepository.getFavItemsByUsername_Set(username);
        Page<Item> favoritos = clienteFavoritosRepository.getFavItemsByUsername(username, PageRequest.of(page, number));
        for(Item i : favoritos){
            if(i.getSubclasse().toLowerCase(Locale.ROOT).equals("calcado") && isNumeric(dimensoes) && i.getDimensao().equals(dimensoes)){
                res.add(i);
            } else if (!i.getSubclasse().toLowerCase(Locale.ROOT).equals("calcado") && !isNumeric(dimensoes) && i.getDimensao().equals(dimensoes)) {
                res.add(i);
            }
        }
        return res.stream().map(x->favoritoItemMapper.toFavoritoItemDTO(x)).collect(Collectors.toList());
    }


    public void addFavorito(FavoritoItemInsertDTO item) throws ItemCodeAlreadyExists{
        Optional<Cliente> c = checkIfClientExists(item.getClienteUsername());
        Optional<Loja> l = optionalLoja(item.getIdLoja());
        Loja loja;
        if(l.isPresent()) loja = l.get();
        else{
            loja = new Loja(item.getIdLoja());
            lojaRepository.save(loja);
        }
        if(c.isPresent()){
            if(!c.get().getFavoritos().isEmpty() && c.get().getFavoritos().stream().anyMatch(x -> x.getCodigo().equals(item.getCodigoItem()) && x.getLoja().getIdentificador() == item.getIdLoja())) throw new ItemCodeAlreadyExists(item.getCodigoItem());
            Item i = new Item(item.getCodigoItem(),item.getDesignacao(),item.getPreco(),item.getDisponibilidade(),item.getTipo(),item.getImagem(),item.getSubclasse(),item.getDimensao(),loja);
            c.get().addFavorite(i);
            clienteFavoritosRepository.save(c.get());
        }else {
            Item i = new Item(item.getCodigoItem(),item.getDesignacao(),item.getPreco(),item.getDisponibilidade(),item.getTipo(),item.getImagem(),item.getSubclasse(),item.getDimensao(),loja);
            Cliente newcliente = new Cliente(item.getClienteUsername(),new HashSet<>());
            newcliente.addFavorite(i);
            clienteFavoritosRepository.save(newcliente);
        }
    }

    public void removeFavorito(FavoritoItemRemoveDTO item) throws ItemCodeAlreadyExists, InexistentFavourite {
        Optional<Cliente> c = checkIfClientExists(item.getUsername());

        if(c.isPresent()){
            if(!c.get().getFavoritos().isEmpty() && c.get().getFavoritos().stream().anyMatch(x -> x.getCodigo().equals(item.getItemCode()) && x.getLoja().getIdentificador() == item.getIdLoja())){
                c.get().removeFavorite(item.getItemCode());
                clienteFavoritosRepository.save(c.get());
            }
            else throw new InexistentFavourite();
        }
        else throw new InexistentFavourite();
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
