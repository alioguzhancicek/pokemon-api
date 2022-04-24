package com.example.alioguzhancicek.pokemonapi.mapper;

import com.example.alioguzhancicek.pokemonapi.controller.response.PokemonListResponse;
import com.example.alioguzhancicek.pokemonapi.repository.entity.PokemonEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PokemonListResponseMapper extends BaseMapper<PokemonEntity, PokemonListResponse> {
}
