package com.example.notificacoesService.controllers;

import com.example.notificacoesService.dto.ClientItemDTO;
import com.example.notificacoesService.dto.EncomendaInsertDTO;
import com.example.notificacoesService.services.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.notificacoesService.dto.NotificationDTO; 
import org.springframework.data.domain.PageRequest;
import java.util.List;

@RestController
@RequestMapping("/api/notificacoes")
public class NotificationsController {

    private NotificationsService notificationsService;

    @Autowired
    public NotificationsController(NotificationsService notificationsService){
        this.notificationsService = notificationsService;
    }

    //quando o utilizador pressiona o sino do item
    @PostMapping("/addItemInterested")
    public void adicionarInteressado(@RequestBody ClientItemDTO clientItemDTO){
        notificationsService.addInterested(clientItemDTO);
    }

    @PostMapping("/addEncomenda")
    public void adicionarEncomenda(@RequestBody EncomendaInsertDTO encomendainsertdto){
        notificationsService.encomendaInsertDTO(encomendainsertdto);
    }

    @GetMapping("/getAllNotificationsFromClient/{username}")
    public List<NotificationDTO> getAllNotificationsFromClient(@PathVariable String username, @RequestParam Integer page, @RequestParam Integer number){
        return notificationsService.getAllNotificationsFromClient(username,page,number);
    }

    @DeleteMapping("/removeNotificationFromClientByID/{username}")
    public void removeNotificationFromClientByID(@RequestParam String username, @RequestParam Integer codigo){
        notificationsService.removeNotificationFromClientByID(username,codigo);
    }

    @DeleteMapping("/clearNotificationsFromClient/{username}")
    public void clearNotificationsFromClient(@RequestParam String username){
        notificationsService.clearNotificationsFromClient(username);
    }

}
