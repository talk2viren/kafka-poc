package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
//        kafkaTemplate.send("test-topic-2","vir-3","Virendra Message 1 failed");
        kafkaTemplate.send("test-topic-1","vir-3","Virendra Message test-topic-1 failed");


    }
}