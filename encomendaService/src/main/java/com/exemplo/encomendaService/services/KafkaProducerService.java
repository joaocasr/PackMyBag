package com.exemplo.encomendaService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.exemplo.encomendaService.dto.EncomendaNotificationDTO;

@Service
public class KafkaProducerService {

    private final String TOPIC = "notificacaoDevolucao"; 

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(EncomendaNotificationDTO encomenda) {
        this.kafkaTemplate.send(TOPIC, encomenda.toString());
    }
}
