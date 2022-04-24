package com.example.alioguzhancicek.pokemonapi.repository;

import com.example.alioguzhancicek.pokemonapi.repository.entity.PokemonEntity;
import com.example.alioguzhancicek.pokemonapi.repository.entity.PokemonTypeEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PokemonRepository extends JpaRepository<PokemonEntity, Long>, JpaSpecificationExecutor<PokemonEntity> {

    List<PokemonEntity> findByTypesContaining(PokemonTypeEntity pokemonTypeEntity, Sort sort);

}
