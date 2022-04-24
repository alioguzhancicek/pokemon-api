package com.example.alioguzhancicek.pokemonapi.repository;

import com.example.alioguzhancicek.pokemonapi.repository.entity.PokemonTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonTypeRepository extends JpaRepository<PokemonTypeEntity, Long> {
    PokemonTypeEntity findByName(String name);
}
