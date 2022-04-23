package com.example.alioguzhancicek.pokemonapi.repository.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
}
