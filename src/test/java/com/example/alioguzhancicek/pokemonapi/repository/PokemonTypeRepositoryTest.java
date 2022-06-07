package com.example.alioguzhancicek.pokemonapi.repository;

import com.example.alioguzhancicek.pokemonapi.model.entity.PokemonTypeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class PokemonTypeRepositoryTest {

    @Autowired
    PokemonTypeRepository pokemonTypeRepository;

    @Test
    void findByName() {
        PokemonTypeEntity pokemonTypeEntity = new PokemonTypeEntity("testType");
        pokemonTypeRepository.save(pokemonTypeEntity);
        assertThat(pokemonTypeRepository.findByName("testType")).isNotNull();
    }
}