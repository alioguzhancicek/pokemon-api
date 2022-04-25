package com.example.alioguzhancicek.pokemonapi.mapper;

import com.example.alioguzhancicek.pokemonapi.model.dto.response.PokemonDetailResponse;
import com.example.alioguzhancicek.pokemonapi.model.entity.PokemonEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PokemonDetailResponseMapper extends BaseMapper<PokemonEntity, PokemonDetailResponse> {
}
