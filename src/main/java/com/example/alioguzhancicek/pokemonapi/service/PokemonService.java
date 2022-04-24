package com.example.alioguzhancicek.pokemonapi.service;

import com.example.alioguzhancicek.pokemonapi.controller.request.GetPokemonTypesRequest;
import com.example.alioguzhancicek.pokemonapi.controller.request.GetPokemonsRequest;
import com.example.alioguzhancicek.pokemonapi.controller.response.PokemonListResponse;
import com.example.alioguzhancicek.pokemonapi.controller.response.PokemonTypeResponse;
import com.example.alioguzhancicek.pokemonapi.mapper.PokemonListResponseMapper;
import com.example.alioguzhancicek.pokemonapi.mapper.PokemonTypeEntityMapper;
import com.example.alioguzhancicek.pokemonapi.repository.PokemonRepository;
import com.example.alioguzhancicek.pokemonapi.repository.PokemonTypeRepository;
import com.example.alioguzhancicek.pokemonapi.repository.entity.PokemonEntity;
import com.example.alioguzhancicek.pokemonapi.repository.entity.PokemonTypeEntity;
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
    private final PokemonTypeRepository pokemonTypeRepository;
    private final PokemonRepository pokemonRepository;
    private final PokemonTypeEntityMapper pokemonTypeEntityMapper = Mappers.getMapper(PokemonTypeEntityMapper.class);
    private final PokemonListResponseMapper pokemonListResponseMapper = Mappers.getMapper(PokemonListResponseMapper.class);

    public List<PokemonTypeResponse> getPokemonTypes(GetPokemonTypesRequest request) {
        List<PokemonTypeEntity> pokemonTypeEntities = pokemonTypeRepository.findAll(Sort.by(request.getSortDir(), request.getSortCol()));
        return pokemonTypeEntityMapper.map(pokemonTypeEntities);
    }

    public List<PokemonListResponse> getAllByType(GetPokemonsRequest request) {
        List<PokemonEntity> pokemons;
        if (CollectionUtils.isEmpty(request.getFilterMap()) || !StringUtils.hasText(request.getFilterMap().get("type"))) {
            pokemons = pokemonRepository.findAll(Sort.by(request.getSortDir(), request.getSortCol()));
        } else {
            pokemons = pokemonRepository.findByTypesContaining(
                    pokemonTypeRepository.findByName(request.getFilterMap().get("type")
                    ), Sort.by(request.getSortDir(), request.getSortCol()));
        }
        return pokemonListResponseMapper.map(pokemons);
    }
}
