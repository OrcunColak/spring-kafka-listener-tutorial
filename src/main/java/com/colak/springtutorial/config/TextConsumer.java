package com.colak.springtutorial.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TextConsumer {

    private static final String INPUT_TOPIC = "TEXT-DATA";


    @KafkaListener(topics = INPUT_TOPIC, groupId = "TEXT_CONSUMERS")
    public void consumeMessage(String message) {
        log.info("Message : {}", message);
    }
}