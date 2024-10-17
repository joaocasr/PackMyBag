package com.example.favoritosService.services;

import com.example.favoritosService.dto.ClienteDTO;
import com.example.favoritosService.dto.FavoritoItemDTO;
import com.example.favoritosService.dto.FavoritoItemInsertDTO;
import com.example.favoritosService.dto.FavoritoItemRemoveDTO;
import com.example.favoritosService.exceptions.ItemCodeAlreadyExists;
import com.example.favoritosService.exceptions.NoCatalogItemsException;
import com.example.favoritosService.exceptions.NoCatalogItemsGenderException;
import com.example.favoritosService.exceptions.NoCatalogItemsPriceException;
import com.example.favoritosService.model.Cliente;
import com.example.favoritosService.model.Item;
import com.example.favoritosService.mappers.FavoritoItemMapper;
import com.example.favoritosService.repositories.ClienteFavoritosRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FavoritosService {

    private FavoritoItemMapper favoritoItemMapper;
    private ClienteFavoritosRepository clienteFavoritosRepository;

    public FavoritosService(ClienteFavoritosRepository clienteFavoritosRepository, FavoritoItemMapper favoritoItemMapper){
        this.favoritoItemMapper = favoritoItemMapper;
        this.clienteFavoritosRepository = clienteFavoritosRepository;
    }

    private boolean checkIfClientExists(String username){
        Cliente c = clienteFavoritosRepository.getClienteByUsername(username);
        return c != null;
    }


    public List<FavoritoItemDTO> getUserFavourites(ClienteDTO clienteDTO, int page, int number) throws NoCatalogItemsException {
        return clienteFavoritosRepository.getFavItemsByUsername(clienteDTO.getUsername(), PageRequest.of(page, number)).stream().map(x->favoritoItemMapper.toFavoritoItemDTO(x)).collect(Collectors.toList());
    }

    public List<FavoritoItemDTO> getPerGenderItems(String username, String tipo) throws NoCatalogItemsGenderException {
        List<Item> res = new ArrayList<>();
        Set<Item> favoritos = clienteFavoritosRepository.getFavItemsByUsername_Set(username);
        for(Item i : favoritos){
            if(i.getTipo().equals(tipo)){
                res.add(i);
            }
        }
        return res.stream().map(x->favoritoItemMapper.toFavoritoItemDTO(x)).collect(Collectors.toList());
    }

    public List<FavoritoItemDTO> getPerPriceItems(String username, int min, int max) throws NoCatalogItemsPriceException {
        List<Item> res = new ArrayList<>();
        Set<Item> favoritos = clienteFavoritosRepository.getFavItemsByUsername_Set(username);
        for(Item i : favoritos){
            if(min <= i.getPreco() && i.getPreco() <= max){
                res.add(i);
            }
        }
        return res.stream().map(x->favoritoItemMapper.toFavoritoItemDTO(x)).collect(Collectors.toList());
    }

    public List<FavoritoItemDTO> getPerSizeItems(String username, String dimensoes) throws NoCatalogItemsPriceException {
        List<Item> res = new ArrayList<>();
        Set<Item> favoritos = clienteFavoritosRepository.getFavItemsByUsername_Set(username);
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
        if(checkIfClientExists(item.getClienteUsername())){
            Cliente c = clienteFavoritosRepository.getClienteByUsername(item.getClienteUsername());
            if(c.getFavoritos().stream().map(Item::getCodigo).anyMatch(x->x.equals(item.getCodigoItem()))) throw new ItemCodeAlreadyExists(item.getCodigoItem());
            Item i = new Item(item.getCodigoItem(),item.getDesignacao(),item.getPreco(),item.getDisponibilidade(),item.getTipo(),item.getImagem(),item.getSubclasse(),item.getDimensao());
            c.addFavorite(i);
            clienteFavoritosRepository.save(c);
        }else {
            Item i = new Item(item.getCodigoItem(),item.getDesignacao(),item.getPreco(),item.getDisponibilidade(),item.getTipo(),item.getImagem(),item.getSubclasse(),item.getDimensao());
            Cliente c = new Cliente(item.getClienteUsername(),new HashSet<>());
            c.addFavorite(i);
            clienteFavoritosRepository.save(c);
        }
    }

    public void removeFavorito(FavoritoItemRemoveDTO item) throws ItemCodeAlreadyExists{
        if(checkIfClientExists(item.getUsername())){
            Cliente c = clienteFavoritosRepository.getClienteByUsername(item.getUsername());
            if(c.getFavoritos().stream().map(Item::getCodigo).anyMatch(x->x.equals(item.getItemCode()))){
                c.removeFavorite(item.getItemCode());
                clienteFavoritosRepository.save(c);
            }
        }
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
