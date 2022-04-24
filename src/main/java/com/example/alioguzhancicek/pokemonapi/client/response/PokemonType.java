package com.example.alioguzhancicek.pokemonapi.client.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PokemonType {
    private int slot;
    private NamedAPIResource type;
}
