package com.example.alioguzhancicek.pokemonapi.client;

import com.example.alioguzhancicek.pokemonapi.client.response.PokeApiPokemonResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PokemonClient {

    private final WebClient client = WebClient.builder()
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    @PostConstruct
    private List<PokeApiPokemonResponse> get() {

        Flux<PokeApiPokemonResponse> pokeApiPokemonResponse = client.get()
                .uri("https://pokeapi.co/api/v2/pokemon?limit=151")
                .retrieve()
                .bodyToFlux(PokeApiPokemonResponse.class);

        return pokeApiPokemonResponse
                .collect(Collectors.toList())
                .share().block();
    }
}
