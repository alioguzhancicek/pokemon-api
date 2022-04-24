package com.example.alioguzhancicek.pokemonapi.client.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PokemonHeldItem {
    private NamedAPIResource item;
    private List<PokemonHeldItemVersion> version_details;
}
