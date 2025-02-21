package com.example;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class Producer {

    @Bean
    public NewTopic newTopic(){
        return TopicBuilder.name("new-topic")
                .partitions(1)
                .replicas(1)
                .compact()
                .build();
    }
    public static void main(String[] args) {
        SpringApplication.run(Producer.class, args);
    }
}
