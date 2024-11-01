package com.exemplo.encomendaService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.exemplo.encomendaService.dto.EncomendaDateReturnDTO;
import com.exemplo.encomendaService.dto.EncomendaStatusDTO;

@Service
public class KafkaProducerService {

    private final String TOPIC = "notificacaoDevolucao"; 

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(Object encomenda, String topic) {

        if (encomenda instanceof EncomendaDateReturnDTO) {
            EncomendaDateReturnDTO encomendaNotificationDTO = (EncomendaDateReturnDTO) encomenda;
            this.kafkaTemplate.send(TOPIC, encomendaNotificationDTO.toString());
        } else if (encomenda instanceof EncomendaStatusDTO) {
            EncomendaStatusDTO encomendaStatusDTO = (EncomendaStatusDTO) encomenda;
            this.kafkaTemplate.send(TOPIC, encomendaStatusDTO.toString());
        }

    }

}
