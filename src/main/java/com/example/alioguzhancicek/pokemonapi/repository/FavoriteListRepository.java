package com.example.alioguzhancicek.pokemonapi.repository;

import com.example.alioguzhancicek.pokemonapi.repository.entity.FavoriteListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteListRepository extends JpaRepository<FavoriteListEntity, Long> {
}
