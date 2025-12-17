package com.anaparthi.path_tracker.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PathTrackerProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public PathTrackerProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send("learning-events", message);
    }
}