package com.example.notificacoesService.services;

import com.example.notificacoesService.dto.*;
import com.example.notificacoesService.model.*;
import com.example.notificacoesService.repositories.ClienteRepository;
import com.example.notificacoesService.repositories.EncomendaRepository;
import com.example.notificacoesService.repositories.ItemRepository;
import com.example.notificacoesService.repositories.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.util.Optional;



@Service
public class NotificationsService implements NotificationCallback {

    private ClienteRepository clienteRepository;
    private ItemRepository itemRepository;
    private NotificacaoRepository notificacaoRepository;
    private EncomendaRepository encomendaRepository;
    private RestTemplate restTemplate;

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
        String gatewayUrl = "http://localhost:8888/api/notificacoesService/getnotifications/"+n.getCliente().getUsername();
        restTemplate.postForObject(gatewayUrl, new NotificationMSG(n.getTipo(),n.getDescricao(),n.getData(),n.getCliente().getUsername()), String.class);
        notificacaoRepository.save(n);
    }

    @Transactional
    public void notifyOrderStatus(String codigoEncomenda, String status) {
        Optional<Encomenda> e = encomendaRepository.getEncomendabyCode(codigoEncomenda);
        Encomenda encomenda =null;
        if(e.isPresent()) {
            encomenda = e.get();
            encomenda.setStatus(status);
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
        this.notifyOrderStatus(attrs[1], attrs[2]);
    }

    @Transactional
    @KafkaListener(topics = "EncomendaDateReturn", groupId = "notificacoesService")
    public void consumeEncomendaDateReturn(String dateReturnDTO) {
        System.out.println(dateReturnDTO);
        String [] attrs = dateReturnDTO.split(",");
        notifyClientAboutReturnDate(attrs[1], Integer.parseInt(attrs[6]));
    }

}

