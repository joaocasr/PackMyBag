package com.example.catalogService.services;

import com.example.catalogService.dto.ItemUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    public final static String TOPIC = "ItemUpdate";

    @Autowired
    public KafkaTemplate<String , String> kafkaTemplate;

    public void sendMessage(ItemUpdate item){
        this.kafkaTemplate.send(TOPIC, item.toString());
    }
}
