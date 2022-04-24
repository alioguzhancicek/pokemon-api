package com.example.alioguzhancicek.pokemonapi.mapper;

import com.example.alioguzhancicek.pokemonapi.controller.response.PokemonDetailResponse;
import com.example.alioguzhancicek.pokemonapi.repository.entity.PokemonEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PokemonDetailResponseMapper extends BaseMapper<PokemonEntity, PokemonDetailResponse> {
}
