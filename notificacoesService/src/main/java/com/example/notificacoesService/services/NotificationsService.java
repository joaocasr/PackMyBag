package com.example.notificacoesService.services;

import com.example.notificacoesService.dto.ClientItemDTO;
import com.example.notificacoesService.dto.ItemUpdate;
import com.example.notificacoesService.model.*;
import com.example.notificacoesService.repositories.ClienteRepository;
import com.example.notificacoesService.repositories.ItemRepository;
import com.example.notificacoesService.repositories.NotificacaoRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class NotificationsService {
    private ClienteRepository clienteRepository;
    private ItemRepository itemRepository;
    private NotificacaoRepository notificacaoRepository;
    private RestTemplate restTemplate;

    public NotificationsService(ClienteRepository clienteRepository,ItemRepository itemRepository,NotificacaoRepository notificacaoRepository,RestTemplate restTemplate){
        this.clienteRepository = clienteRepository;
        this.itemRepository = itemRepository;
        this.notificacaoRepository = notificacaoRepository;
        this.restTemplate = restTemplate;
    }


    public void addInterested(ClientItemDTO clientItemDTO){
        Cliente c = clienteRepository.getClienteByUsername(clientItemDTO.getUsername());
        if(c==null) c = new Cliente(clientItemDTO.getNomeuser(),clientItemDTO.getEmail(),clientItemDTO.getUsername());


        Item i = itemRepository.getItemShop(clientItemDTO.getIDLoja(),clientItemDTO.getCodigo());
        if(i==null) i = new Item(clientItemDTO.getCodigo(),clientItemDTO.getDesignacao(),clientItemDTO.getDisponibilidade(),clientItemDTO.getIDLoja());
        c.addItem(i);
        i.registerObserver(c);
        itemRepository.save(i);
        clienteRepository.save(c);

    }

    @Transactional
    public void notifyItemChange(ItemUpdate itemUpdate){
        Item i = itemRepository.getItemShop(String.valueOf(itemUpdate.getIdloja()),itemUpdate.getCode());
        if(i!=null){
            i.setDisponibilidade(itemUpdate.getDisponibilidade());
            i.notifyObservers();

            for(Cliente c : i.getInteressados()){
                List<Notificacao> l = new java.util.ArrayList<>(c.getNotificacoes().stream().toList());
                l.sort(new SortByDate());
                Notificacao n = l.get(l.size()-1);
                n.setCliente(c);
                System.out.println(n);
                notificacaoRepository.save(n);
                sendNotificationToGateway(n);
                System.out.println("SENT");
            }
        }

    }

    @Transactional
    @KafkaListener(topics = "ItemUpdate", groupId = "notificacoesService")
    public void handleMsgfromBroker(String msg){
        System.out.println(msg);
        if(msg.contains("ItemUpdate")){
            String [] attrs = msg.split(",");
            this.notifyItemChange(new ItemUpdate(Integer.parseInt(attrs[2]),attrs[1],attrs[3]));
        }
    }

    private void sendNotificationToGateway(Notificacao n) {
        String gatewayUrl = "http://localhost:8888/api/notificacoesService/getnotifications/"+n.getCliente().getUsername();
        restTemplate.postForObject(gatewayUrl, new NotificationMSG(n.getTipo(),n.getDescricao(),n.getData(),n.getCliente().getUsername()), String.class);
    }

}

