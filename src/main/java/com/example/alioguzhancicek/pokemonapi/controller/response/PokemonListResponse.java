package com.example.alioguzhancicek.pokemonapi.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PokemonListResponse {
    private Long id;
    private String name;
    private String spritesFrontDefault;
}
