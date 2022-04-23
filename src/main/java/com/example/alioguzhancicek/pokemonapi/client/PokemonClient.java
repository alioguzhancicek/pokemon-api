package com.example.alioguzhancicek.pokemonapi.client;

import com.example.alioguzhancicek.pokemonapi.client.response.PokeApiBaseResponse;
import com.example.alioguzhancicek.pokemonapi.repository.PokemonTypeRepository;
import com.example.alioguzhancicek.pokemonapi.repository.entity.PokemonTypeEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class PokemonClient {

    private final PokemonTypeRepository pokemonTypeRepository;

    private final WebClient client = WebClient.builder()
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    @PostConstruct
    private void fetchAndPersist() {
        fetchAndPersistPokemonTypes();

    }

    private void fetchAndPersistPokemonTypes() {
        PokeApiBaseResponse pokemonTypesResponse = client.get()
                .uri("https://pokeapi.co/api/v2/type")
                .retrieve()
                .bodyToMono(PokeApiBaseResponse.class).log().block();

        List<PokemonTypeEntity> pokemonTypeEntities = pokemonTypesResponse.getResults().stream()
                .map(pt -> new PokemonTypeEntity(pt.getName()))
                .collect(Collectors.toList());

        pokemonTypeRepository.saveAll(pokemonTypeEntities);
    }
}
