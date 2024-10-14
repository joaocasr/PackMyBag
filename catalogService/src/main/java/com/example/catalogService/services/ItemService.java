package com.example.catalogService.services;

import com.example.catalogService.dto.CatalogoItemDTO;
import com.example.catalogService.dto.SetInsertDTO;
import com.example.catalogService.dto.PecaInsertDTO;
import com.example.catalogService.exceptions.ItemCodeAlreadyExists;
import com.example.catalogService.exceptions.NoCatalogItemsException;
import com.example.catalogService.exceptions.NoCatalogItemsGenderException;
import com.example.catalogService.exceptions.NoCatalogItemsPriceException;
import com.example.catalogService.mappers.ItemMapper;
import com.example.catalogService.model.Set;
import com.example.catalogService.model.Peca;
import com.example.catalogService.model.Loja;
import com.example.catalogService.repositories.ItemRepository;
import com.example.catalogService.repositories.LojaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private ItemRepository itemRepository;
    private LojaRepository lojaRepository;
    private ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository, LojaRepository lojaRepository, ItemMapper itemMapper){
        this.itemRepository = itemRepository;
        this.lojaRepository = lojaRepository;
        this.itemMapper = itemMapper;
    }

    private boolean checkIfItemCodeAlreadyExists(String code,int idLoja){
        return !itemRepository.getItemsByCodeShop(code,idLoja).isEmpty();
    }

    public List<CatalogoItemDTO> getAllItems() throws NoCatalogItemsException {
        return itemRepository.findAll(PageRequest.of(0, 12)).stream().map(x->itemMapper.toCatalogoItemDTO(x)).collect(Collectors.toList());
    }

    public List<CatalogoItemDTO> getItemsByPage(int page,int number) throws NoCatalogItemsException {
        return itemRepository.findAll(PageRequest.of(page, number)).stream().map(x->itemMapper.toCatalogoItemDTO(x)).collect(Collectors.toList());
    }

    public List<CatalogoItemDTO> getPerGenderItems(int page, int number,String gender) throws NoCatalogItemsGenderException {
        return itemRepository.getItemsByGender(gender,PageRequest.of(page, number)).stream().map(x->itemMapper.toCatalogoItemDTO(x)).collect(Collectors.toList());
    }

    public List<CatalogoItemDTO> getPerPriceItems(int page, int number,int min, int max) throws NoCatalogItemsPriceException {
        return itemRepository.getItemsByPrice(min,max,PageRequest.of(page, number)).stream().map(x->itemMapper.toCatalogoItemDTO(x)).collect(Collectors.toList());
    }

    public List<CatalogoItemDTO> getPerPriceandGenderItems(int page, int number,int min, int max,String gender) throws NoCatalogItemsGenderException {
        return itemRepository.getItemsByPriceandGender(min,max,gender,PageRequest.of(page, number)).stream().map(x->itemMapper.toCatalogoItemDTO(x)).collect(Collectors.toList());
    }

    // criar metodo para devolver itens de uma loja, o técnico dessa loja só poderá criar sets com essas pecas da loja a que está associado
    //public List<CatalogoItemDTO> getItemsofShop(idLoja)

    public void savePeca(PecaInsertDTO item) throws ItemCodeAlreadyExists{
        if(checkIfItemCodeAlreadyExists(item.getCodigo(),item.getIdLoja())) throw new ItemCodeAlreadyExists(item.getCodigo());
        Optional<Loja> loja = this.lojaRepository.findById(item.getIdLoja());
        if(loja.isPresent()){
            StringBuilder cor = new StringBuilder();
            for(String c:item.getCores()){
                cor.append(c).append(",");
            }
            itemRepository.save(new Peca(loja.get(),item.getCodigo(),item.getDesignacao(),item.getPreco(),0,item.getEstilo(),cor.substring(0,cor.length()-1),item.getTamanho(),item.getGenero(),item.getDisponibilidade(),item.getImagem()));
        }
    }

    public void saveSet(SetInsertDTO item) throws ItemCodeAlreadyExists{
        if(checkIfItemCodeAlreadyExists(item.getCodigo(),item.getIdLoja())) throw new ItemCodeAlreadyExists(item.getCodigo());
        Optional<Loja> loja = this.lojaRepository.findById(item.getIdLoja());

        if(loja.isPresent()) {

            java.util.Set<Peca> pecas = new HashSet();
            for (String codigo : item.getCodigoPecas()) {
                Peca peca = (Peca) this.itemRepository.getItemsByCodeShop(codigo, item.getIdLoja()).stream().toList().get(0);
                pecas.add(peca);
            }
            List<String> cores = pecas.stream().map(x->x.getCor().split(",")).flatMap(Arrays::stream).toList().stream().distinct().toList();
            System.out.println("cores");
            System.out.println(cores);
            StringBuilder cor = new StringBuilder();
            for(String c : cores){
                cor.append(c).append(",");
            }
            Set conjunto = new Set(pecas.size(), pecas,
                    loja.get(),item.getCodigo(),item.getDesignacao(),item.getPreco(),0,item.getEstilo(),cor.substring(0,cor.length()-1),item.getTamanho(),item.getGenero(),item.getDisponibilidade(),item.getImagem());
            itemRepository.save(conjunto);
            for(Peca p:pecas){
                java.util.Set conjuntos = p.getSets();
                conjuntos.add(conjunto);
                p.setSets(conjuntos);
                itemRepository.save(p);
            }
        }
    }
}
