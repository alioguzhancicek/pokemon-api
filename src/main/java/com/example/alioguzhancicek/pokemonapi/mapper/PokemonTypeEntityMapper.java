package com.example.alioguzhancicek.pokemonapi.mapper;

import com.example.alioguzhancicek.pokemonapi.model.dto.response.PokemonTypeResponse;
import com.example.alioguzhancicek.pokemonapi.model.entity.PokemonTypeEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PokemonTypeEntityMapper extends BaseMapper<PokemonTypeEntity, PokemonTypeResponse> {
}
