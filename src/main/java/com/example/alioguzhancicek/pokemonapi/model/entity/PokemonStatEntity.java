package com.example.alioguzhancicek.pokemonapi.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pokemon_stat")
@Builder
public class PokemonStatEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "base_stat", nullable = false)
    private Integer baseStat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pokemon_id", referencedColumnName = "id")
    private PokemonEntity pokemon;
}
