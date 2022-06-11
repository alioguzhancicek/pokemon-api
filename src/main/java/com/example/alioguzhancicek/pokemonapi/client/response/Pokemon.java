package com.example.alioguzhancicek.pokemonapi.client.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Pokemon implements Serializable {
    private int id;
    private String name;
    private int base_experience;
    private int height;
    private boolean is_default;
    private int order;
    private int weight;
    private List<PokemonAbility> abilities;
    private List<NamedAPIResource> forms;
    private List<VersionGameIndex> game_indices;
    private List<PokemonHeldItem> held_items;
    private String location_area_encounters;
    private List<PokemonMove> moves;
    private List<PokemonTypePast> past_types;
    private PokemonSprites sprites;
    private NamedAPIResource species;
    private List<PokemonStat> stats;
    private List<PokemonType> types;
}
