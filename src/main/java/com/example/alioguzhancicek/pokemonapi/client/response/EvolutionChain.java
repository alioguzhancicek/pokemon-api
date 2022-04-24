package com.example.alioguzhancicek.pokemonapi.client.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EvolutionChain {
    private int id;
    private NamedAPIResource baby_trigger_item;
    private ChainLink chain;
}
