package com.example.alioguzhancicek.pokemonapi.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PokemonDetailResponse {
    private int id;
    private String name;
    private int height;
    private int weight;
    private List<PokemonStatResponse> stats;
    private List<PokemonTypeResponse> types;
    private String spritesFrontDefault;
    private String spritesBackDefault;
}
