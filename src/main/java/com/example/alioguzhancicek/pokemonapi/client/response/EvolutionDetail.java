package com.example.alioguzhancicek.pokemonapi.client.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EvolutionDetail {
    private NamedAPIResource item;
    private NamedAPIResource trigger;
    private int gender;
    private NamedAPIResource held_item;
    private NamedAPIResource known_move;
    private NamedAPIResource known_move_type;
    private NamedAPIResource location;
    private int min_level;
    private int min_happiness;
    private int min_beauty;
    private int min_affection;
    private boolean needs_overworld_rain;
    private NamedAPIResource party_species;
    private NamedAPIResource party_type;
    private int relative_physical_stats;
    private String time_of_day;
    private NamedAPIResource trade_species;
    private boolean turn_upside_down;

}
