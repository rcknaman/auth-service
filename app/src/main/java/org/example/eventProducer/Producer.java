package org.example.eventProducer;

import org.example.models.UserInfoDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private final KafkaTemplate<String, UserInfoDto> kafkaTemplate;

    // Spring Boot auto-config injects KafkaTemplate automatically
    public Producer(KafkaTemplate<String, UserInfoDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic,  UserInfoDto message) {
        kafkaTemplate.send(topic, message);
        System.out.println("Sent message = " + message + " to topic = " + topic);
    }
}
