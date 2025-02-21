package com.example.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "test-topic",groupId = "my-group")
    public void listen(String message){
//        throw new RuntimeException("exception in consumer");
        System.out.println("Received message >> "+message);
    }
}
