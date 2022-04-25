package com.example.alioguzhancicek.pokemonapi.service;

import com.example.alioguzhancicek.pokemonapi.mapper.PokemonTypeEntityMapper;
import com.example.alioguzhancicek.pokemonapi.model.dto.request.GetPokemonTypesRequest;
import com.example.alioguzhancicek.pokemonapi.model.dto.response.PokemonTypeResponse;
import com.example.alioguzhancicek.pokemonapi.model.entity.PokemonTypeEntity;
import com.example.alioguzhancicek.pokemonapi.repository.PokemonTypeRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PokemonTypeService {

    private final PokemonTypeRepository pokemonTypeRepository;

    private final PokemonTypeEntityMapper pokemonTypeEntityMapper = Mappers.getMapper(PokemonTypeEntityMapper.class);

    public int count() {
        return (int) pokemonTypeRepository.count();
    }

    public void saveAll(List<PokemonTypeEntity> pokemonTypeEntities) {
        pokemonTypeRepository.saveAll(pokemonTypeEntities);
    }

    public PokemonTypeEntity findByName(String name) {
        return pokemonTypeRepository.findByName(name);
    }

    public List<PokemonTypeResponse> getPokemonTypes(GetPokemonTypesRequest request) {
        List<PokemonTypeEntity> pokemonTypeEntities = pokemonTypeRepository.findAll(Sort.by(request.getSortDir(), request.getSortCol()));
        return pokemonTypeEntityMapper.map(pokemonTypeEntities);
    }
}
