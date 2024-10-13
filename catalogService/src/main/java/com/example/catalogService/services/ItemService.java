package com.example.catalogService.services;

import com.example.catalogService.dto.CatalogoItemDTO;
import com.example.catalogService.dto.PecaInsertDTO;
import com.example.catalogService.exceptions.NoCatalogItemsException;
import com.example.catalogService.exceptions.NoCatalogItemsGenderException;
import com.example.catalogService.exceptions.NoCatalogItemsPriceException;
import com.example.catalogService.mappers.ItemMapper;
import com.example.catalogService.model.Peca;
import com.example.catalogService.model.Loja;
import com.example.catalogService.repositories.ItemRepository;
import com.example.catalogService.repositories.LojaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public void saveItem(PecaInsertDTO item){

        Optional<Loja> loja = this.lojaRepository.findById(item.getIdLoja());
        if(loja.isPresent()){
            StringBuilder cor = new StringBuilder();
            for(String c:item.getCores()){
                cor.append(c).append(",");
            }
            itemRepository.save(new Peca(loja.get(),item.getCodigo(),item.getDesignacao(),item.getPreco(),0,item.getEstilo(),cor.substring(0,cor.length()-2),item.getTamanho(),item.getGenero(),item.getDisponibilidade(),item.getImagem()));

        }
    }
}
