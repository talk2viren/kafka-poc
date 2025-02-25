package com.example.service;

import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.util.backoff.FixedBackOff;

@Service
public class KafkaConsumerService {


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
