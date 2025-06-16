package com.product_backend.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(prefix = "product_service.kafka", name = "enabled", havingValue = "true")
public class KafkaConsumer {
    
    @KafkaListener(
        topics = "${product_service.kafka.topic.user.orchestration}", 
        groupId = "${product_service.kafka.consumer.group-id}"
    )
    public void consume(String message){
        System.out.println("Consumed message = "+message);
    }
}
