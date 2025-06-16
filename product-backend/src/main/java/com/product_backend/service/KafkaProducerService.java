package com.product_backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(prefix = "product_service.kafka", name = "enabled", havingValue = "true")
public class KafkaProducerService {

    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${product_service.kafka.topic.user.orchestration}")
    private String topic;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(String message){
        kafkaTemplate.send(topic, message);
    }
    
}
