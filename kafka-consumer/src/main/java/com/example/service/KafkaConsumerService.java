package com.example.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "test-topic-1")
    public void listen1(String message){

        System.out.println("Inside test-topic-1 >>> "+message);
        throw  new RuntimeException("Exception thrown");
    }

    @KafkaListener(topics = "test-topic-2", groupId = "my-group", containerFactory = "kafkaListenerContainerFactory")
    public void listen(String message, @Header(KafkaHeaders.DELIVERY_ATTEMPT) int attempt) {
        System.out.println("Attempt #" + attempt + " - Received message: " + message);

        if (message.contains("fail")) {
            throw new IllegalArgumentException("Processing failed for: " + message);
        }
    }

    @KafkaListener(topics = "test-topic-2-dlt")
    public void listenToDlt(String message) {
        System.out.println("Sent to DLQ: " + message);
    }


}
