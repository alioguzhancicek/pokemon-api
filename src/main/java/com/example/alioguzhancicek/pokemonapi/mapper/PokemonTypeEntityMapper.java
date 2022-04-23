package com.example.alioguzhancicek.pokemonapi.mapper;

import com.example.alioguzhancicek.pokemonapi.controller.response.PokemonTypeResponse;
import com.example.alioguzhancicek.pokemonapi.repository.entity.PokemonTypeEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PokemonTypeEntityMapper extends BaseMapper<PokemonTypeEntity, PokemonTypeResponse> {
}
