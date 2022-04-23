//package com.example.alioguzhancicek.pokemonapi.repository;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "pokemon_stat")
//public class PokemonStatEntity extends BaseEntity{
//
//    @Column(name = "base_stat", nullable = false)
//    private Integer baseStat;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "pokemon_id", referencedColumnName = "id")
//    private PokemonEntity pokemon;
//}
