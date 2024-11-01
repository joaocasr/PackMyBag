package com.example.notificacoesService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.example.notificacoesService.model.Encomenda;
import com.example.notificacoesService.dto.EncomendaStatusDTO;
import com.example.notificacoesService.dto.EncomendaDateReturnDTO;

@Service
public class KafkaConsumerService {

    @Autowired
    private EncomendaService encomendaService;

    @KafkaListener(topics = "EncomendaStatus", groupId = "notificacoesService")
    public void consumeEncomendaStatus(EncomendaStatusDTO statusDTO) {

        encomendaService.updateEncomendaStatus(statusDTO.getIdEncomenda(), statusDTO.getStatus());
    }

    @KafkaListener(topics = "EncomendaDateReturn", groupId = "notificacoesService")
    public void consumeEncomendaDateReturn(EncomendaDateReturnDTO dateReturnDTO) {

        encomendaService.notifyClientAboutReturnDate(dateReturnDTO.getIdEncomenda(), dateReturnDTO.getTempoRestante());
    }
}
