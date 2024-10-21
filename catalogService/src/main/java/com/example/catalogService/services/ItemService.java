package com.example.catalogService.services;

import com.example.catalogService.dto.*;
import com.example.catalogService.exceptions.*;
import com.example.catalogService.mappers.ItemMapper;
import com.example.catalogService.model.*;
import com.example.catalogService.model.Set;
import com.example.catalogService.repositories.ClientRepository;
import com.example.catalogService.repositories.ItemRepository;
import com.example.catalogService.repositories.LojaRepository;
import com.example.catalogService.repositories.ReviewRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private ItemRepository itemRepository;
    private ReviewRepository reviewRepository;
    private LojaRepository lojaRepository;
    private ClientRepository clientRepository;
    private ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository,ReviewRepository reviewRepository, LojaRepository lojaRepository, ClientRepository clientRepository,ItemMapper itemMapper){
        this.itemRepository = itemRepository;
        this.reviewRepository = reviewRepository;
        this.lojaRepository = lojaRepository;
        this.clientRepository = clientRepository;
        this.itemMapper = itemMapper;
    }

    private boolean checkIfItemCodeAlreadyExists(String code,int idLoja){
        return !itemRepository.getItemsByCodeShop(code,idLoja).isEmpty();
    }

    public List<CatalogoItemDTO> getAllItems() throws NoCatalogItemsException {
        return itemRepository.findAll().stream().map(x->itemMapper.toCatalogoItemDTO(x)).collect(Collectors.toList());
    }

    public List<CatalogoItemDTO> getItemsByPage(int page,int number) throws NoCatalogItemsException {
        return itemRepository.findAll(PageRequest.of(page, number)).stream().map(x->itemMapper.toCatalogoItemDTO(x)).collect(Collectors.toList());
    }

    public List<CatalogoItemDTO> getPerTypeItems(int page, int number,String type) throws NoCatalogItemsGenderException {
        return itemRepository.getItemsByType(type,PageRequest.of(page, number)).stream().map(x->itemMapper.toCatalogoItemDTO(x)).collect(Collectors.toList());
    }

    public List<CatalogoItemDTO> getPerPriceItems(int page, int number,int min, int max) throws NoCatalogItemsPriceException {
        return itemRepository.getItemsByPrice(min,max,PageRequest.of(page, number)).stream().map(x->itemMapper.toCatalogoItemDTO(x)).collect(Collectors.toList());
    }

    public List<CatalogoItemDTO> getPerPriceandTypeItems(int page, int number,int min, int max,String type) throws NoCatalogItemsGenderException {
        return itemRepository.getItemsByPriceandType(min,max,type,PageRequest.of(page, number)).stream().map(x->itemMapper.toCatalogoItemDTO(x)).collect(Collectors.toList());
    }

    // criar metodo para devolver itens de uma loja, o técnico dessa loja só poderá criar sets com essas pecas da loja a que está associado
    //public List<CatalogoItemDTO> getItemsFromShop(idLoja)
    public List<CatalogoItemDTO>  getItemsFromShop(int idloja,int page, int number) throws NoCatalogItemsException{
        return itemRepository.getShopItems(idloja,PageRequest.of(page, number)).stream().map(x->itemMapper.toCatalogoItemDTO(x)).collect(Collectors.toList());
    }

    public FullDetailedItemDTO getItem(int id) throws InexistentItemException {
        Optional<Item> item  = itemRepository.findById(id);
        if(item.isEmpty()) throw new InexistentItemException(id);
        Item full = null;
        Item i = item.get();
        int nrreviews = i.getCriticas().size();
        if(i instanceof Peca ){
            full = new Peca(i.getLoja(),i.getCodigo(),i.getDesignacao(),i.getPreco(),i.getNraquisicoes(),i.getEstilo(),i.getCor(),((Peca) i).getTamanho(),i.getTipo(),i.getDisponibilidade(),i.getImagem(),((Peca) i).getSets(),i.getNrDisponiveis());
        }
        if(i instanceof Set ){
            full= new Set(((Set) i).getNrPecas(),((Set) i).getPecas(),i.getLoja(),i.getCodigo(),i.getDesignacao(),i.getPreco(),i.getNraquisicoes(),i.getEstilo(),i.getCor(),((Set) i).getTamanho(),i.getTipo(),i.getDisponibilidade(),i.getImagem());
        }
        if(i instanceof Calcado ){
            full= new Calcado(i.getLoja(),i.getCodigo(),i.getDesignacao(),i.getPreco(),i.getNraquisicoes(),i.getEstilo(),i.getCor(),i.getTipo(),i.getDisponibilidade(),i.getImagem(),i.getNrDisponiveis(),((Calcado) i).getNumero());
        }
        return itemMapper.toFullCatalogoDTO(full,nrreviews);
    }

    public void savePeca(PecaInsertDTO item) throws ItemCodeAlreadyExists{
        if(checkIfItemCodeAlreadyExists(item.getCodigo(),item.getIdLoja())) throw new ItemCodeAlreadyExists(item.getCodigo());
        Optional<Loja> loja = this.lojaRepository.findById(item.getIdLoja());
        if(loja.isPresent()){
            StringBuilder cor = new StringBuilder();
            for(String c:item.getCores()){
                cor.append(c).append("/");
            }
            itemRepository.save(new Peca(loja.get(),item.getCodigo(),item.getDesignacao(),item.getPreco(),0,item.getEstilo(),cor.substring(0,cor.length()-1),item.getTamanho(),item.getTipo(),item.getDisponibilidade(),item.getImagem(), item.getNrdisponiveis()));
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
            List<String> separadas = new ArrayList<>();
            List<String> cores = new ArrayList<>();
            for( Peca p : pecas){
                if(p.getCor().contains("/")) {
                    separadas = pecas.stream().map(x -> x.getCor().split("/")).flatMap(Arrays::stream).toList().stream().distinct().toList();
                    cores.addAll(separadas);
                }
                else cores.add(p.getCor());
            }
            StringBuilder cor = new StringBuilder();
            for(String c : cores){
                cor.append(c).append("/");
            }
            Set conjunto = new Set(pecas.size(), pecas,
                    loja.get(),item.getCodigo(),item.getDesignacao(),item.getPreco(),0,item.getEstilo(),cor.substring(0,cor.length()-1),item.getTamanho(),item.getTipo(),item.getDisponibilidade(),item.getImagem());
            itemRepository.save(conjunto);
            for(Peca p:pecas){
                java.util.Set conjuntos = p.getSets();
                conjuntos.add(conjunto);
                p.setSets(conjuntos);
                itemRepository.save(p);
            }
        }
    }

    public void saveCalcado(CalcadoInsertDTO calcadoInsertDTO) throws ItemCodeAlreadyExists {
        if(checkIfItemCodeAlreadyExists(calcadoInsertDTO.getCodigo(),calcadoInsertDTO.getIdLoja())) throw new ItemCodeAlreadyExists(calcadoInsertDTO.getCodigo());
        Optional<Loja> loja = this.lojaRepository.findById(calcadoInsertDTO.getIdLoja());
        if(loja.isPresent()){
            StringBuilder cor = new StringBuilder();
            for(String c:calcadoInsertDTO.getCores()){
                cor.append(c).append("/");
            }
            itemRepository.save(new Calcado(loja.get(),calcadoInsertDTO.getCodigo(),calcadoInsertDTO.getDesignacao(),calcadoInsertDTO.getPreco(),0,calcadoInsertDTO.getEstilo(),cor.substring(0,cor.length()-1),calcadoInsertDTO.getTipo(),calcadoInsertDTO.getDisponibilidade(),calcadoInsertDTO.getImagem(), calcadoInsertDTO.getNrdisponiveis(), calcadoInsertDTO.getTamanho()));
        }

    }

    public void removeItem(RemoveItemDTO removeItemDTO) throws InexistentItemCodeException{
        if(!checkIfItemCodeAlreadyExists(removeItemDTO.getCode(), removeItemDTO.getLojaid())) throw new InexistentItemCodeException(removeItemDTO.getCode());
        java.util.Set<Item> items = itemRepository.getItemsByCodeShop(removeItemDTO.getCode(),removeItemDTO.getLojaid());
        Item i = items.stream().toList().get(0);
        if(i instanceof Peca){
            for(Set s : ((Peca) i).getSets()){
                s.removePeca(i.getCodigo());
                itemRepository.save(s);
            }
        }
        if(i instanceof Set){
            for(Peca p : ((Set) i).getPecas()){
                p.removeSet(i.getCodigo());
                itemRepository.save(p);
            }
        }
        itemRepository.deleteItemCodeShop(i.getCodigo(),i.getLoja().getIDLoja());
    }


    public void insertReview(InsertReviewDTO insertReviewDTO,int itemID) throws InexistentItemException {
        Optional<Item> i = itemRepository.findById(itemID);
        if(i.isEmpty()) throw new InexistentItemException(itemID);
        Item item = i.get();
        Cliente c = clientRepository.getClienteByUsername(insertReviewDTO.getUsername());
        if(c==null) {
            c = new Cliente(insertReviewDTO.getName(),insertReviewDTO.getUsername(),insertReviewDTO.getProfileImg());
            clientRepository.save(c);
        }
        Review r = new Review(c,insertReviewDTO.getRating(), insertReviewDTO.getTimestamp(),insertReviewDTO.getTexto());
        if(item.getCriticas().stream().map(x->x.getAutor().getUsername()).anyMatch(x->x.equals(insertReviewDTO.getUsername()))){
            Review oldreview = item.getCriticas().stream().filter(x->x.getAutor().getUsername().equals(insertReviewDTO.getUsername())).findFirst().get();
            oldreview.setClassificacao(insertReviewDTO.getRating());
            oldreview.setDescricao(insertReviewDTO.getTexto());
            oldreview.setData(insertReviewDTO.getTimestamp());
            item.adicionaReview(oldreview);
            reviewRepository.save(oldreview);
            itemRepository.save(item);
        }else{
            item.adicionaReview(r);
            reviewRepository.save(r);
            itemRepository.save(item);
        }
    }

    public List<ReviewDTO> getReviews(int id, int page, int number) {
        return itemRepository.getReviews(id,PageRequest.of(page,number)).stream().map(x->itemMapper.toReviewDTO(x)).toList();
    }

    public void removeReview(int id , String username) throws InexistentItemException {
        Optional<Item> i = itemRepository.findById(id);
        if(i.isEmpty()) throw new InexistentItemException(id);
        Item item = i.get();
        Review r = item.getCriticas().stream().filter(x->x.getAutor().getUsername().equals(username)).toList().get(0);
        reviewRepository.deleteReviewById(r.getIdReview());
    }
}
