package com.example.alioguzhancicek.pokemonapi.client.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PokemonStat {
    private NamedAPIResource stat;
    private int effort;
    private int base_stat;
}
