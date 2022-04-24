package com.example.alioguzhancicek.pokemonapi.client.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChainLink {
    private boolean is_baby;
    private NamedAPIResource species;
    private List<EvolutionDetail> evolution_details;
    private List<ChainLink> evolves_to;
}
