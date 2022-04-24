package com.example.alioguzhancicek.pokemonapi.client.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VersionGameIndex {
    private int game_index;
    private NamedAPIResource version;
}
