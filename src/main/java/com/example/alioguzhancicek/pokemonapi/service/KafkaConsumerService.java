package com.example.alioguzhancicek.pokemonapi.service;

import com.example.alioguzhancicek.pokemonapi.client.response.Pokemon;
import com.example.alioguzhancicek.pokemonapi.client.response.PokemonStat;
import com.example.alioguzhancicek.pokemonapi.client.response.PokemonType;
import com.example.alioguzhancicek.pokemonapi.model.entity.PokemonEntity;
import com.example.alioguzhancicek.pokemonapi.model.entity.PokemonStatEntity;
import com.example.alioguzhancicek.pokemonapi.model.entity.PokemonTypeEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final PokemonTypeService pokemonTypeService;
    private final PokemonService pokemonService;

    @KafkaListener(topics = "#{'${spring.kafka.consumer.topic.name}'}",
            containerFactory = "kafkaListenerContainerFactory")
    public void consume(Pokemon pokemonResponse) {
        log.info(String.format("Pokemon received -> %s", pokemonResponse.getName()));


        PokemonEntity pokemonEntity = PokemonEntity.builder()
                .name(pokemonResponse.getName())
                .height(pokemonResponse.getHeight())
                .weight(pokemonResponse.getWeight())
                .stats(new ArrayList<>())
                .types(new HashSet<>())
                .spritesFrontDefault(pokemonResponse.getSprites().getFront_default())
                .spritesBackDefault(pokemonResponse.getSprites().getBack_default())
                .build();

        for (PokemonStat pokemonStat : pokemonResponse.getStats()) {
            PokemonStatEntity pokemonStatEntity = PokemonStatEntity.builder()
                    .pokemon(pokemonEntity)
                    .baseStat(pokemonStat.getBase_stat())
                    .name(pokemonStat.getStat().getName()).build();

            pokemonEntity.getStats().add(pokemonStatEntity);
        }

        List<PokemonTypeEntity> pokemonTypeEntities = pokemonTypeService.findAll();

        Set<PokemonTypeEntity> types = new HashSet<>();

        for (PokemonType pokemonType : pokemonResponse.getTypes()) {
            types.add(pokemonTypeEntities.stream().filter(pte -> pte.getName().equals(pokemonType.getType().getName())).findFirst().get());
        }
        pokemonEntity.setTypes(types);

        pokemonService.save(pokemonEntity);
    }
}