package com.example.alioguzhancicek.pokemonapi.service;

import com.example.alioguzhancicek.pokemonapi.controller.request.FavoriteListRequest;
import com.example.alioguzhancicek.pokemonapi.controller.request.GetPokemonTypesRequest;
import com.example.alioguzhancicek.pokemonapi.controller.request.GetPokemonsRequest;
import com.example.alioguzhancicek.pokemonapi.controller.response.PokemonDetailResponse;
import com.example.alioguzhancicek.pokemonapi.controller.response.PokemonListResponse;
import com.example.alioguzhancicek.pokemonapi.controller.response.PokemonTypeResponse;
import com.example.alioguzhancicek.pokemonapi.exception.NoSuchPokemonException;
import com.example.alioguzhancicek.pokemonapi.mapper.PokemonDetailResponseMapper;
import com.example.alioguzhancicek.pokemonapi.mapper.PokemonListResponseMapper;
import com.example.alioguzhancicek.pokemonapi.mapper.PokemonTypeEntityMapper;
import com.example.alioguzhancicek.pokemonapi.repository.FavoriteListRepository;
import com.example.alioguzhancicek.pokemonapi.repository.PokemonRepository;
import com.example.alioguzhancicek.pokemonapi.repository.PokemonTypeRepository;
import com.example.alioguzhancicek.pokemonapi.repository.entity.FavoriteListEntity;
import com.example.alioguzhancicek.pokemonapi.repository.entity.PokemonEntity;
import com.example.alioguzhancicek.pokemonapi.repository.entity.PokemonTypeEntity;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PokemonService {
    private final PokemonTypeRepository pokemonTypeRepository;
    private final PokemonRepository pokemonRepository;
    private final FavoriteListRepository favoriteListRepository;

    private final PokemonTypeEntityMapper pokemonTypeEntityMapper = Mappers.getMapper(PokemonTypeEntityMapper.class);
    private final PokemonListResponseMapper pokemonListResponseMapper = Mappers.getMapper(PokemonListResponseMapper.class);
    private final PokemonDetailResponseMapper pokemonDetailResponseMapper = Mappers.getMapper(PokemonDetailResponseMapper.class);

    public List<PokemonTypeResponse> getPokemonTypes(GetPokemonTypesRequest request) {
        List<PokemonTypeEntity> pokemonTypeEntities = pokemonTypeRepository.findAll(Sort.by(request.getSortDir(), request.getSortCol()));
        return pokemonTypeEntityMapper.map(pokemonTypeEntities);
    }

    public List<PokemonListResponse> getAllByType(GetPokemonsRequest request) {
        List<PokemonEntity> pokemons;
        if (CollectionUtils.isEmpty(request.getFilterMap()) || !StringUtils.hasText(request.getFilterMap().get("type"))) {
            pokemons = pokemonRepository.findAll(Sort.by(request.getSortDir(), request.getSortCol()));
        } else {
            pokemons = pokemonRepository.findByTypesContaining(
                    pokemonTypeRepository.findByName(request.getFilterMap().get("type")
                    ), Sort.by(request.getSortDir(), request.getSortCol()));
        }
        return pokemonListResponseMapper.map(pokemons);
    }

    public PokemonDetailResponse get(String idOrName) {
        PokemonEntity pokemon;

        try {
            pokemon = pokemonRepository.findById(Long.parseLong(idOrName)).get();
        } catch (Exception e) {
            pokemon = pokemonRepository.findByName(idOrName);
        }

        return pokemonDetailResponseMapper.map(pokemon);
    }

    public void createFavoriteList(FavoriteListRequest request) {
        FavoriteListEntity favoriteListEntity = new FavoriteListEntity();
        favoriteListEntity.setName(request.getName());
        favoriteListEntity.setPokemons(prepareFavoritePokemonEntities(request));
        favoriteListRepository.save(favoriteListEntity);
    }

    public void addToFavoriteList(FavoriteListRequest request) {
        FavoriteListEntity favoriteListEntity = favoriteListRepository.findByName(request.getName());
        favoriteListEntity.getPokemons().addAll(prepareFavoritePokemonEntities(request));
        favoriteListRepository.save(favoriteListEntity);
    }

    public void deleteFromFavoriteList(FavoriteListRequest request) {
        FavoriteListEntity favoriteListEntity = favoriteListRepository.findByName(request.getName());
        favoriteListEntity.getPokemons().removeAll(prepareFavoritePokemonEntities(request));
        favoriteListRepository.save(favoriteListEntity);
    }

    private List<PokemonEntity> prepareFavoritePokemonEntities(FavoriteListRequest request) {
        List<String> favoritePokemonNames = request.getPokemons();
        List<PokemonEntity> favoritePokemonEntities = new ArrayList<>();

        for (String favoritePokemonName : favoritePokemonNames) {
            PokemonEntity pokemon = pokemonRepository.findByName(favoritePokemonName);
            if (pokemon == null) {
                throw new NoSuchPokemonException("Pokemon not found with the name " + favoritePokemonName);
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
