package com.example.alioguzhancicek.pokemonapi.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "pokemon_type")
public class PokemonTypeEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "types")
    private Set<PokemonEntity> pokemons;

    public PokemonTypeEntity(String name) {
        this.name = name;
    }
}
