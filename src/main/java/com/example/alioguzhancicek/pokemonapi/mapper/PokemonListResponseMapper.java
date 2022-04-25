package com.example.alioguzhancicek.pokemonapi.mapper;

import com.example.alioguzhancicek.pokemonapi.model.dto.response.PokemonListResponse;
import com.example.alioguzhancicek.pokemonapi.model.entity.PokemonEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PokemonListResponseMapper extends BaseMapper<PokemonEntity, PokemonListResponse> {
}
