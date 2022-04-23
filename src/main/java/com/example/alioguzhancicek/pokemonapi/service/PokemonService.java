package com.example.alioguzhancicek.pokemonapi.service;

import com.example.alioguzhancicek.pokemonapi.controller.request.GetPokemonTypesRequest;
import com.example.alioguzhancicek.pokemonapi.controller.response.PokemonTypeResponse;
import com.example.alioguzhancicek.pokemonapi.mapper.PokemonTypeEntityMapper;
import com.example.alioguzhancicek.pokemonapi.repository.PokemonTypeRepository;
import com.example.alioguzhancicek.pokemonapi.repository.entity.PokemonTypeEntity;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PokemonService {

    private final PokemonTypeRepository pokemonTypeRepository;

    private final PokemonTypeEntityMapper pokemonTypeEntityMapper = Mappers.getMapper(PokemonTypeEntityMapper.class);

    public List<PokemonTypeResponse> getPokemonTypes(GetPokemonTypesRequest request) {
        List<PokemonTypeEntity> pokemonTypeEntities = pokemonTypeRepository.findAll(Sort.by(request.getSortDir(), request.getSortCol()));
        return pokemonTypeEntityMapper.map(pokemonTypeEntities);
    }
}
