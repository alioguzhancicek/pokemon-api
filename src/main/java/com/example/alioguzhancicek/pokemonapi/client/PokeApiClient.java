package com.example.alioguzhancicek.pokemonapi.client;

import com.example.alioguzhancicek.pokemonapi.client.response.PokeApiBaseResponse;
import com.example.alioguzhancicek.pokemonapi.client.response.Pokemon;
import com.example.alioguzhancicek.pokemonapi.client.response.PokemonStat;
import com.example.alioguzhancicek.pokemonapi.client.response.PokemonType;
import com.example.alioguzhancicek.pokemonapi.model.entity.PokemonEntity;
import com.example.alioguzhancicek.pokemonapi.model.entity.PokemonStatEntity;
import com.example.alioguzhancicek.pokemonapi.model.entity.PokemonTypeEntity;
import com.example.alioguzhancicek.pokemonapi.service.PokemonService;
import com.example.alioguzhancicek.pokemonapi.service.PokemonTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class PokeApiClient {

    @Value("${pokemon-limit:151}")
    private int pokemonLimit;

    private final PokemonService pokemonService;
    private final PokemonTypeService pokemonTypeService;

    private WebClient client = WebClient.builder()
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    @PostConstruct
    private void postConstruct() {
        client = client.mutate().codecs(configurer -> configurer
                .defaultCodecs()
                .maxInMemorySize(16 * 1024 * 1024)).build();

        fetchAndPersistPokemonTypes();

        int fetchFrom = pokemonService.pokemonCount() + 1;

        if (fetchFrom > pokemonLimit) {
            return;
        }

        for (int i = fetchFrom; i <= pokemonLimit; i++) {
            fetchAndPersistPokemon(i);
        }
    }


    private void fetchAndPersistPokemonTypes() {
        PokeApiBaseResponse pokemonTypesResponse = client.get()
                .uri("https://pokeapi.co/api/v2/type")
                .retrieve()
                .bodyToMono(PokeApiBaseResponse.class).log().block();

        List<String> allPokemonTypes = pokemonTypesResponse.getResults().stream().map(r -> r.getName()).collect(Collectors.toList());

        int allPokemonTypesCount = allPokemonTypes.size();
        int savedPokemonTypesCount = pokemonTypeService.count();

        if (savedPokemonTypesCount >= allPokemonTypesCount) {
            return;
        }

        List<PokemonTypeEntity> pokemonTypeEntities =
                allPokemonTypes.subList(savedPokemonTypesCount, allPokemonTypesCount)
                        .stream().map(PokemonTypeEntity::new).collect(Collectors.toList());

        pokemonTypeService.saveAll(pokemonTypeEntities);
    }

    private void fetchAndPersistPokemon(int id) {
        Pokemon pokemonResponse = client.get()
                .uri("https://pokeapi.co/api/v2/pokemon/" + id)
                .retrieve()
                .bodyToMono(Pokemon.class).log().block();

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
