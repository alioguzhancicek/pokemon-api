package com.example.alioguzhancicek.pokemonapi.repository;

import com.example.alioguzhancicek.pokemonapi.model.entity.PokemonEntity;
import com.example.alioguzhancicek.pokemonapi.model.entity.PokemonTypeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@DataJpaTest
class PokemonRepositoryTest {

    @Autowired
    PokemonRepository pokemonRepository;

    @Autowired
    PokemonTypeRepository pokemonTypeRepository;

    @Test
    void findByTypesContaining() {
        PokemonTypeEntity pokemonTypeEntity = new PokemonTypeEntity("testType");
        PokemonEntity pokemonEntity = PokemonEntity.builder()
                .name("ali")
                .height(15)
                .weight(16)
                .spritesBackDefault("dasdasd")
                .spritesFrontDefault("dasdasda")
                .types(Set.of(pokemonTypeEntity)).build();

        pokemonTypeRepository.save(pokemonTypeEntity);
        pokemonRepository.save(pokemonEntity);

        assertThat(pokemonRepository.findByTypesContaining(pokemonTypeEntity, any())).isNotEmpty();
    }

    @Test
    void findByName() {
    }
}