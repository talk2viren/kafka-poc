package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaResourceFactory;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest
class ProducerTest {

    private final KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    public ProducerTest(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Test
    void test1() {
        kafkaTemplate.send("test-topic","Test Message - 7");


    }
}