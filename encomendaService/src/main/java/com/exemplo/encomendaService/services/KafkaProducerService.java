package com.exemplo.encomendaService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.exemplo.encomendaService.dto.EncomendaDateReturnDTO;
import com.exemplo.encomendaService.dto.EncomendaStatusDTO;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String o, String topic) {
        this.kafkaTemplate.send(topic,o);
    }

}
