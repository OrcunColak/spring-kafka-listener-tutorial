package com.colak.springtutorial.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class TextConsumer {

    private static final String INPUT_TOPIC = "TEXT-DATA";


    // Read as Pojo
    @KafkaListener(topics = INPUT_TOPIC, groupId = "TEXT_CONSUMERS")
    public void consumeMessage(String message) {
        log.info("Message : {}", message);
    }

    // Read as ConsumerRecord
    @KafkaListener(topics = INPUT_TOPIC, groupId = "TEXT_CONSUMERS")
    public void consumeMessage1(ConsumerRecord<String, String> consumerRecord) {
        log.info("Key : {} Message : {}", consumerRecord.key(), consumerRecord.value());
    }

    // Read as Pojo list and other metadata
    @KafkaListener(topics = INPUT_TOPIC, groupId = "TEXT_CONSUMERS")
    public void consumeMessage2(@Payload List<String> messages,
                                @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                                @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                                @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("Messages : {}", messages);
        log.info("Keys : {}", keys);
        log.info("Partitions : {}", partitions);
        log.info("Offsets : {}", offsets);
    }
}