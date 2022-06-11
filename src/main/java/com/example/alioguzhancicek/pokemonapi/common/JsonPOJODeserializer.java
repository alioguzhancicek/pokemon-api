package com.example.alioguzhancicek.pokemonapi.common;

import com.example.alioguzhancicek.pokemonapi.client.response.Pokemon;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.serialization.Deserializer;

import javax.annotation.PostConstruct;

public class JsonPOJODeserializer implements Deserializer<Pokemon> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public Pokemon deserialize(String topic, byte[] bytes) {
        if (bytes == null) return null;

        try {
            return objectMapper.readValue(bytes, Pokemon.class);
        } catch (Exception e) {
        }

        return null;
    }
}
