package com.example.alioguzhancicek.pokemonapi.client.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PokemonTypePast {
    private NamedAPIResource generation;
    private List<PokemonType> types;
}
