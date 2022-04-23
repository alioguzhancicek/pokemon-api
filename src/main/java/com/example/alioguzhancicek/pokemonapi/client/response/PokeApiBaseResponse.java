package com.example.alioguzhancicek.pokemonapi.client.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PokeApiBaseResponse {
    private int count;
    private String next;
    private String previous;
    private List<NamedAPIResource> results;
}
