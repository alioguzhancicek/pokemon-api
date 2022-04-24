package com.example.alioguzhancicek.pokemonapi.client.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PokemonMoveVersion {
    private NamedAPIResource move_learn_method;
    private NamedAPIResource version_group;
    private int level_learned_at;
}
