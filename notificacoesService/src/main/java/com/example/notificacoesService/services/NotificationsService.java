package com.example.notificacoesService.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.notificacoesService.dto.ClientItemDTO;
import com.example.notificacoesService.dto.EncomendaInsertDTO;
import com.example.notificacoesService.dto.ItemUpdate;
import com.example.notificacoesService.dto.NotificationDTO;
import com.example.notificacoesService.model.Cliente;
import com.example.notificacoesService.model.Encomenda;
import com.example.notificacoesService.model.Item;
import com.example.notificacoesService.model.Notificacao;
import com.example.notificacoesService.model.NotificationCallback;
import com.example.notificacoesService.model.NotificationMSG;
import com.example.notificacoesService.repositories.ClienteRepository;
import com.example.notificacoesService.repositories.EncomendaRepository;
import com.example.notificacoesService.repositories.ItemRepository;
import com.example.notificacoesService.repositories.NotificacaoRepository;



@Service
public class NotificationsService implements NotificationCallback {

    private ClienteRepository clienteRepository;
    private ItemRepository itemRepository;
    private NotificacaoRepository notificacaoRepository;
    private EncomendaRepository encomendaRepository;
    private RestTemplate restTemplate;

    @Autowired
    public NotificationsService(ClienteRepository clienteRepository,ItemRepository itemRepository,NotificacaoRepository notificacaoRepository,RestTemplate restTemplate, EncomendaRepository encomendaRepository){
        this.clienteRepository = clienteRepository;
        this.itemRepository = itemRepository;
        this.notificacaoRepository = notificacaoRepository;
        this.restTemplate = restTemplate;
        this.encomendaRepository = encomendaRepository;
    }


    public void addInterested(ClientItemDTO clientItemDTO){
        Cliente c;
        Optional<Cliente> cliente = clienteRepository.getClienteByUsername(clientItemDTO.getUsername());
        c = cliente.orElseGet(() -> new Cliente(clientItemDTO.getNomeuser(), clientItemDTO.getEmail(), clientItemDTO.getUsername()));

        Item i = itemRepository.getItemShop(clientItemDTO.getIDLoja(),clientItemDTO.getCodigo());
        if(i==null) i = new Item(clientItemDTO.getCodigo(),clientItemDTO.getDesignacao(),clientItemDTO.getDisponibilidade(),clientItemDTO.getIDLoja());
        c.addItem(i);
        i.registerObserver(c);
        itemRepository.save(i);
        clienteRepository.save(c);

    }

    public void encomendaInsertDTO(EncomendaInsertDTO encomendainsertdto){
        Encomenda encomenda;
        Cliente c;

        Optional<Encomenda> e = encomendaRepository.getEncomendabyCode(encomendainsertdto.getUsernameCliente());
        Optional<Cliente> cliente = clienteRepository.getClienteByUsername(encomendainsertdto.getUsernameCliente());
               
        if(cliente.isEmpty()) {
            c = new Cliente(encomendainsertdto.getNomeCliente(),encomendainsertdto.getEmailCliente(),encomendainsertdto.getUsernameCliente());
            clienteRepository.save(c);
        }
        else c = cliente.get();

        encomenda = e.orElseGet(() -> new Encomenda(c, encomendainsertdto.getCodigoEncomenda(), encomendainsertdto.getStatus(), encomendainsertdto.getDiasRestantes()));

        encomendaRepository.save(encomenda);
    }



    @Transactional
    @Override
    public void handleNewNotification(Notificacao n) {
        // aqui se nao estiver dentro do dockercompose vai dar erro o apigatewayservice tem de ser localhost
        String gatewayUrl = "http://apigatewayservice:8888/api/notificacoesService/getnotifications/"+n.getCliente().getUsername();
        restTemplate.postForObject(gatewayUrl, new NotificationMSG(n.getTipo(),n.getDescricao(),n.getData(),n.getCliente().getUsername()), String.class);
        notificacaoRepository.save(n);
    }

    @Transactional
    public void notifyOrderStatus(String codigoEncomenda, String status, String username) {
        Optional<Encomenda> e = encomendaRepository.getEncomendabyCode(codigoEncomenda);
        Encomenda encomenda =null;
        if(e.isPresent()) {
            encomenda = e.get();
            encomenda.setStatus(status);
            encomendaRepository.save(encomenda);
            encomenda.notifyObservers("STATUS", this);
        }else { 
            Optional<Cliente> c = clienteRepository.getClienteByUsername(username);
            Cliente cliente = null;
            if(c.isPresent()){
                cliente = c.get();
                clienteRepository.save(cliente);
            }else{
                cliente = new Cliente("","",username);
                clienteRepository.save(cliente);
            }
            encomenda = new Encomenda(cliente, codigoEncomenda, status, 1);
            encomendaRepository.save(encomenda);
            encomenda.notifyObservers("STATUS", this);
        }
    }

    @Transactional
    public void notifyClientAboutReturnDate(String codigo, int tempoRestante) {
        Optional<Encomenda> e = encomendaRepository.getEncomendabyCode(codigo);
        if(e.isPresent()) {
            Encomenda encomenda = e.get();
            encomenda.setDiasRestantes(tempoRestante);
            encomenda.notifyObservers("RETURN", this);
        }
    }

    @Transactional
    public void removeNotificationFromClient(String username, Notificacao id){
        Optional<Cliente> c = clienteRepository.getClienteByUsername(username);
        if(c.isPresent()){
            Cliente cliente = c.get();
            cliente.removeNotification(id);
            clienteRepository.save(cliente);
        }
    }

    @Transactional
    public void removeNotificationFromClientByID(String username, int codigo){
        Optional<Cliente> c = clienteRepository.getClienteByUsername(username);
        if(c.isPresent()){
            Cliente cliente = c.get();
            cliente.removeNotificationByID(codigo);
            System.out.println(cliente);
            clienteRepository.save(cliente);
            notificacaoRepository.deleteById(codigo);
        }
    }

    @Transactional
    public void clearNotificationsFromClient(String username){
        Optional<Cliente> c = clienteRepository.getClienteByUsername(username);
        if(c.isPresent()){
            Cliente cliente = c.get();
            cliente.setNotificacoes(null);
            clienteRepository.save(cliente);
        }
    }

    @Transactional
    public List<NotificationDTO> getAllNotificationsFromClient(String username, int page, int number){
        Page<Notificacao> p = notificacaoRepository.getNotificationsUser(username,PageRequest.of(page,number));
        if(p.getSize()>0) return p.stream().map(x->new NotificationDTO(x.getTipo(),x.getDescricao(),x.getData(),x.getIDNotificacao())).toList();    
        List<NotificationDTO> l = new ArrayList<>();
        return l;
    }

    @Transactional
    public void notifyItemChange(ItemUpdate itemUpdate){
        Item i = itemRepository.getItemShop(String.valueOf(itemUpdate.getIdloja()),itemUpdate.getCode());
        if(i!=null){
            i.setDisponibilidade(itemUpdate.getDisponibilidade());
            i.notifyObservers("AVAILABILITY",this);
        }
    }

    @Transactional
    @KafkaListener(topics = "ItemUpdate", groupId = "notificacoesService")
    public void handleMsgfromBroker(String msg){
        System.out.println(msg);
        String [] attrs = msg.split(",");
        this.notifyItemChange(new ItemUpdate(Integer.parseInt(attrs[2]),attrs[1],attrs[3]));

    }

    @Transactional
    @KafkaListener(topics = "EncomendaStatus", groupId = "notificacoesService")
    public void consumeEncomendaStatus(String statusDTO) {
        System.out.println(statusDTO);
        String [] attrs = statusDTO.split(",");
        this.notifyOrderStatus(attrs[1], attrs[2], attrs[3]);
    }

    @Transactional
    @KafkaListener(topics = "EncomendaDateReturn", groupId = "notificacoesService")
    public void consumeEncomendaDateReturn(String dateReturnDTO) {
        System.out.println(dateReturnDTO);
        String [] attrs = dateReturnDTO.split(",");
        notifyClientAboutReturnDate(attrs[1], Integer.parseInt(attrs[6]));
    }

}

