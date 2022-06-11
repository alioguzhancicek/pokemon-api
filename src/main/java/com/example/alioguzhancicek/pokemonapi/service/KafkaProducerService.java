package com.example.alioguzhancicek.pokemonapi.service;

import com.example.alioguzhancicek.pokemonapi.client.response.Pokemon;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducerService {

    @Value("${spring.kafka.consumer.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(Pokemon pokemon) {
        log.info(String.format("Pokemon fetched with name -> %s", pokemon.getName()));
        this.kafkaTemplate.send(topicName, pokemon);
    }
}