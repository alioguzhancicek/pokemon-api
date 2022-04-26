package com.example.alioguzhancicek.pokemonapi.service;

import com.example.alioguzhancicek.pokemonapi.exception.PokemonAPIException;
import com.example.alioguzhancicek.pokemonapi.model.dto.request.FavoriteListRequest;
import com.example.alioguzhancicek.pokemonapi.model.entity.FavoriteListEntity;
import com.example.alioguzhancicek.pokemonapi.model.entity.PokemonEntity;
import com.example.alioguzhancicek.pokemonapi.repository.FavoriteListRepository;
import com.example.alioguzhancicek.pokemonapi.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FavoriteListService {

    private final FavoriteListRepository favoriteListRepository;
    private final PokemonRepository pokemonRepository;

    public void create(FavoriteListRequest request) {
        FavoriteListEntity favoriteListEntity = new FavoriteListEntity();
        favoriteListEntity.setName(request.getName());
        favoriteListEntity.setPokemons(prepareFavoritePokemonEntities(request));
        favoriteListRepository.save(favoriteListEntity);
    }

    public void addToFavoriteList(FavoriteListRequest request) {
        FavoriteListEntity favoriteListEntity = checkExistsAndReturnFavoriteListEntity(request.getName());
        favoriteListEntity.getPokemons().addAll(prepareFavoritePokemonEntities(request));
        favoriteListRepository.save(favoriteListEntity);
    }

    public void deleteFromFavoriteList(FavoriteListRequest request) {
        FavoriteListEntity favoriteListEntity = checkExistsAndReturnFavoriteListEntity(request.getName());
        favoriteListEntity.getPokemons().removeAll(prepareFavoritePokemonEntities(request));
        favoriteListRepository.save(favoriteListEntity);
    }

    private FavoriteListEntity checkExistsAndReturnFavoriteListEntity(String name) {
        FavoriteListEntity favoriteListEntity = favoriteListRepository.findByName(name);
        if (favoriteListEntity == null) {
            throw new PokemonAPIException("Favorite list not found with the name " + name);
        }
        return favoriteListEntity;
    }

    private List<PokemonEntity> prepareFavoritePokemonEntities(FavoriteListRequest request) {
        List<String> favoritePokemonNames = request.getPokemons();
        List<PokemonEntity> favoritePokemonEntities = new ArrayList<>();

        for (String favoritePokemonName : favoritePokemonNames) {
            PokemonEntity pokemon = pokemonRepository.findByName(favoritePokemonName);
            if (pokemon == null) {
                throw new PokemonAPIException("Pokemon not found with the name " + favoritePokemonName);
            }
            favoritePokemonEntities.add(pokemon);
        }
        return favoritePokemonEntities;
    }

    @Transactional
    public void deleteFavoriteList(String name) {
        favoriteListRepository.deleteByName(name);
    }
}
