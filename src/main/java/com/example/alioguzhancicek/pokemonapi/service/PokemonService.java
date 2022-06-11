package com.example.alioguzhancicek.pokemonapi.service;

import com.example.alioguzhancicek.pokemonapi.mapper.PokemonDetailResponseMapper;
import com.example.alioguzhancicek.pokemonapi.mapper.PokemonListResponseMapper;
import com.example.alioguzhancicek.pokemonapi.model.dto.request.GetPokemonsRequest;
import com.example.alioguzhancicek.pokemonapi.model.dto.response.PokemonDetailResponse;
import com.example.alioguzhancicek.pokemonapi.model.dto.response.PokemonListResponse;
import com.example.alioguzhancicek.pokemonapi.model.entity.PokemonEntity;
import com.example.alioguzhancicek.pokemonapi.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PokemonService {
    private final PokemonTypeService pokemonTypeService;
    private final PokemonRepository pokemonRepository;

    private final PokemonListResponseMapper pokemonListResponseMapper = Mappers.getMapper(PokemonListResponseMapper.class);
    private final PokemonDetailResponseMapper pokemonDetailResponseMapper = Mappers.getMapper(PokemonDetailResponseMapper.class);

    public List<PokemonListResponse> getAllByType(GetPokemonsRequest request) {
        List<PokemonEntity> pokemons;
        if (CollectionUtils.isEmpty(request.getFilterMap()) || !StringUtils.hasText(request.getFilterMap().get("type"))) {
            pokemons = pokemonRepository.findAll(Sort.by(request.getSortDir(), request.getSortCol()));
        } else {
            pokemons = pokemonRepository.findByTypesContaining(
                    pokemonTypeService.find(request.getFilterMap().get("type")
                    ), Sort.by(request.getSortDir(), request.getSortCol()));
        }
        return pokemonListResponseMapper.map(pokemons);
    }

    public PokemonDetailResponse get(String idOrName) {
        PokemonEntity pokemon;

        try {
            pokemon = pokemonRepository.findById(Long.parseLong(idOrName)).get();
        } catch (Exception e) {
            pokemon = pokemonRepository.findByName(idOrName);
        }

        return pokemonDetailResponseMapper.map(pokemon);
    }

    public int pokemonCount() {
        return (int) pokemonRepository.count();
    }

    public void saveAll(List<PokemonEntity> pokemonEntities) {
        pokemonRepository.saveAll(pokemonEntities);
    }

    public void save(PokemonEntity pokemonEntity) {
        pokemonRepository.save(pokemonEntity);
    }
}
