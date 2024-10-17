package com.example.favoritosService.services;

import com.example.favoritosService.dto.FavoritoItemDTO;
import com.example.favoritosService.dto.FavoritoPecaInsertDTO;
import com.example.favoritosService.exceptions.ItemCodeAlreadyExists;
import com.example.favoritosService.exceptions.NoCatalogItemsException;
import com.example.favoritosService.exceptions.NoCatalogItemsGenderException;
import com.example.favoritosService.exceptions.NoCatalogItemsPriceException;
import com.example.favoritosService.model.Cliente;
import com.example.favoritosService.model.Item;
import com.example.favoritosService.model.Peca;
import com.example.favoritosService.mappers.FavoritoItemMapper;
import com.example.favoritosService.repositories.ClienteFavoritosRepository;
import com.example.favoritosService.repositories.ItemFavoritosRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FavoritosService {

    private ItemFavoritosRepository itemFavoritosRepository;
    private FavoritoItemMapper favoritoItemMapper;
    private ClienteFavoritosRepository clienteFavoritosRepository;

    public FavoritosService(ItemFavoritosRepository itemFavoritoRepository, ClienteFavoritosRepository clienteFavoritosRepository, FavoritoItemMapper favoritoItemMapper){
        this.itemFavoritosRepository = itemFavoritoRepository;
        this.favoritoItemMapper = favoritoItemMapper;
        this.clienteFavoritosRepository = clienteFavoritosRepository;
    }

    private boolean checkIfItemCodeAlreadyExists(String username, String code){
        Cliente c = clienteFavoritosRepository.getClienteByUsername(username);
        return c.getFavoritos().stream().map(x->x.getCodigo()).filter(x->x.equals(code)).count() > 0;
    }

    public List<FavoritoItemDTO> getAllItems() throws NoCatalogItemsException {
        return itemFavoritosRepository.findAll(PageRequest.of(0, 12)).stream().map(x->favoritoItemMapper.toFavoritoItemDTO(x)).collect(Collectors.toList());
    }

    public List<FavoritoItemDTO> getItemsByPage(int page,int number) throws NoCatalogItemsException {
        return itemFavoritosRepository.findAll(PageRequest.of(page, number)).stream().map(x->favoritoItemMapper.toFavoritoItemDTO(x)).collect(Collectors.toList());
    }

    public List<FavoritoItemDTO> getPerGenderItems(String username, String gender) throws NoCatalogItemsGenderException {
        List<Item> res = new ArrayList<>();
        Set<Item> favoritos = clienteFavoritosRepository.getFavItemsByUsername_Set(username);
        for(Item i : favoritos){
            if(i.getGenero().equals(gender)){
                res.add(i);
            }
        }
        return res.stream().map(x->favoritoItemMapper.toFavoritoItemDTO(x)).collect(Collectors.toList());
        //return c.getFavItemsByGender(gender, PageRequest.of(page, number)).stream().map(x->favoritoItemMapper.toFavoritoItemDTO(x)).collect(Collectors.toList());
    }

    public List<FavoritoItemDTO> getPerPriceItems(String username, int min, int max) throws NoCatalogItemsPriceException {
        List<Item> res = new ArrayList<>();
        Set<Item> favoritos = clienteFavoritosRepository.getFavItemsByUsername_Set(username);
        for(Item i : favoritos){
            if(min < i.getPreco() && i.getPreco() <= max){
                res.add(i);
            }
        }
        return res.stream().map(x->favoritoItemMapper.toFavoritoItemDTO(x)).collect(Collectors.toList());
        //return itemFavoritosRepository.getFavItemsByPrice(min,max,PageRequest.of(page, number)).stream().map(x->favoritoItemMapper.toFavoritoItemDTO(x)).collect(Collectors.toList());
    }

    public List<FavoritoItemDTO> getPerSizeItems(String username, String size) throws NoCatalogItemsPriceException {
        List<Item> res = new ArrayList<>();
        Set<Item> favoritos = clienteFavoritosRepository.getFavItemsByUsername_Set(username);
        for(Item i : favoritos){
            if(i.getTamanho().equals(size)){
                res.add(i);
            }
        }
        return res.stream().map(x->favoritoItemMapper.toFavoritoItemDTO(x)).collect(Collectors.toList());
        //return itemFavoritosRepository.getFavItemsByPrice(min,max,PageRequest.of(page, number)).stream().map(x->favoritoItemMapper.toFavoritoItemDTO(x)).collect(Collectors.toList());
    }


    // criar metodo para devolver itens de uma loja, o técnico dessa loja só poderá criar sets com essas pecas da loja a que está associado
    //public List<CatalogoItemDTO> getItemsofShop(idLoja)

    public void addFavorito(FavoritoPecaInsertDTO item) throws ItemCodeAlreadyExists{
        if(checkIfItemCodeAlreadyExists(item.getClienteUsername(), item.getCodigoItem())) throw new ItemCodeAlreadyExists(item.getCodigoItem());
        Cliente c = clienteFavoritosRepository.getClienteByUsername(item.getClienteUsername());
        Item i = itemFavoritosRepository.getItemByCode(item.getCodigoItem());
        c.addFavorite(i);
        clienteFavoritosRepository.save(c);
    }

    public void removeFavorito(FavoritoPecaInsertDTO item) throws ItemCodeAlreadyExists{
        if(checkIfItemCodeAlreadyExists(item.getClienteUsername(), item.getCodigoItem())){
            Cliente c = clienteFavoritosRepository.getClienteByUsername(item.getClienteUsername());
            Item i = itemFavoritosRepository.getItemByCode(item.getCodigoItem());
            c.removeFavorite(i);
            clienteFavoritosRepository.save(c);
        }
    }

}
