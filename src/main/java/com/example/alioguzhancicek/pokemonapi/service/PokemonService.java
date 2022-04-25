package com.example.alioguzhancicek.pokemonapi.service;

import com.example.alioguzhancicek.pokemonapi.exception.NoSuchPokemonException;
import com.example.alioguzhancicek.pokemonapi.mapper.PokemonDetailResponseMapper;
import com.example.alioguzhancicek.pokemonapi.mapper.PokemonListResponseMapper;
import com.example.alioguzhancicek.pokemonapi.model.dto.request.FavoriteListRequest;
import com.example.alioguzhancicek.pokemonapi.model.dto.request.GetPokemonsRequest;
import com.example.alioguzhancicek.pokemonapi.model.dto.response.PokemonDetailResponse;
import com.example.alioguzhancicek.pokemonapi.model.dto.response.PokemonListResponse;
import com.example.alioguzhancicek.pokemonapi.model.entity.FavoriteListEntity;
import com.example.alioguzhancicek.pokemonapi.model.entity.PokemonEntity;
import com.example.alioguzhancicek.pokemonapi.repository.FavoriteListRepository;
import com.example.alioguzhancicek.pokemonapi.repository.PokemonRepository;
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
    private final PokemonTypeService pokemonTypeService;
    private final PokemonRepository pokemonRepository;
    private final FavoriteListRepository favoriteListRepository;

    private final PokemonListResponseMapper pokemonListResponseMapper = Mappers.getMapper(PokemonListResponseMapper.class);
    private final PokemonDetailResponseMapper pokemonDetailResponseMapper = Mappers.getMapper(PokemonDetailResponseMapper.class);

    public List<PokemonListResponse> getAllByType(GetPokemonsRequest request) {
        List<PokemonEntity> pokemons;
        if (CollectionUtils.isEmpty(request.getFilterMap()) || !StringUtils.hasText(request.getFilterMap().get("type"))) {
            pokemons = pokemonRepository.findAll(Sort.by(request.getSortDir(), request.getSortCol()));
        } else {
            pokemons = pokemonRepository.findByTypesContaining(
                    pokemonTypeService.findByName(request.getFilterMap().get("type")
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

    public int pokemonCount() {
        return (int) pokemonRepository.count();
    }

    public void save(PokemonEntity pokemonEntity) {
        pokemonRepository.save(pokemonEntity);
    }
}
