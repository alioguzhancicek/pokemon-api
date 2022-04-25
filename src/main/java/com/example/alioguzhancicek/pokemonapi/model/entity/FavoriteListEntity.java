package com.example.alioguzhancicek.pokemonapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "favorite_list")
public class FavoriteListEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany()
    @JoinTable(
            name = "favorite_list_pokemon_mapping",
            joinColumns = @JoinColumn(name = "favorite_list_id"),
            inverseJoinColumns = @JoinColumn(name = "pokemon_id")
    )
    @JsonIgnoreProperties("pokemons")
    private List<PokemonEntity> pokemons;
}
