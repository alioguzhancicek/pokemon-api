package com.example.alioguzhancicek.pokemonapi.client.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PokemonMove {
    private NamedAPIResource move;
    private List<PokemonMoveVersion> version_group_details;
}
