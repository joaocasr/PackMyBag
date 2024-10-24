package com.example.notificacoesService.controllers;

import com.example.notificacoesService.dto.ClientItemDTO;
import com.example.notificacoesService.services.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
