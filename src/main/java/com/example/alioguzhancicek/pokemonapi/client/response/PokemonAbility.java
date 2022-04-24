package com.example.alioguzhancicek.pokemonapi.client.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PokemonAbility {
    private boolean is_hidden;
    private int slot;
    private NamedAPIResource ability;
}
