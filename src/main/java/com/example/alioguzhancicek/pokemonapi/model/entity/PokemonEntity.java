package com.example.alioguzhancicek.pokemonapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "pokemon")
public class PokemonEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "height", nullable = false)
    private Integer height;

    @Column(name = "weight", nullable = false)
    private Integer weight;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pokemon", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("pokemon")
    private List<PokemonStatEntity> stats = new ArrayList<>();

    @ManyToMany()
    @JoinTable(
            name = "pokemon_type_pokemon_mapping",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "pokemon_type_id")
    )
    @JsonIgnoreProperties("pokemons")
    private Set<PokemonTypeEntity> types;

    @Column(name = "sprites_front_default", nullable = false)
    private String spritesFrontDefault;

    @Column(name = "sprites_back_default", nullable = false)
    private String spritesBackDefault;
}
