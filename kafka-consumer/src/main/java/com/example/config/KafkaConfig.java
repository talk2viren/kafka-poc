package com.example.config;

import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConfig {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory_1(
            ConsumerFactory<String, String> consumerFactory, // Inject auto-configured ConsumerFactory
            KafkaTemplate<String, String> kafkaTemplate) {
        System.out.println("Inside kafka Container Factor @@@@ ");

        return new ConcurrentKafkaListenerContainerFactory<>();
    }  // Inject auto-configured KafkaTemplate



    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
            ConsumerFactory<String, String> consumerFactory, // Inject auto-configured ConsumerFactory
            KafkaTemplate<String, String> kafkaTemplate) {   // Inject auto-configured KafkaTemplate

        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        // Set the auto-configured ConsumerFactory
        factory.setConsumerFactory(consumerFactory);

        // Configure DefaultErrorHandler with retries and DLQ
        DefaultErrorHandler errorHandler = new DefaultErrorHandler(
                new DeadLetterPublishingRecoverer(kafkaTemplate, (record, ex) ->
                        new TopicPartition("test-topic-2-dlt", record.partition())),
                new FixedBackOff(1000L, 3) // 3 retries, 1-second delay
        );
        factory.setCommonErrorHandler(errorHandler);

        return factory;
    }
}
