//package com.example.alioguzhancicek.pokemonapi.repository;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "pokemon")
//public class PokemonEntity extends BaseEntity {
//
//    @Column(name = "name", nullable = false)
//    private String name;
//
//    @Column(name = "height", nullable = false)
//    private Integer height;
//
//    @Column(name = "weight", nullable = false)
//    private Integer weight;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pokemon", cascade = CascadeType.ALL)
//    private List<PokemonStatEntity> stats = new ArrayList<>();
//
//
//}
