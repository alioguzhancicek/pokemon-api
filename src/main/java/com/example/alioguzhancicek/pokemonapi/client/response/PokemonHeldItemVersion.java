package com.example.alioguzhancicek.pokemonapi.client.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PokemonHeldItemVersion {
    private NamedAPIResource version;
    private int rarity;
}
