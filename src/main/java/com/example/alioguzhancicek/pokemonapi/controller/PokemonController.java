package com.example.alioguzhancicek.pokemonapi.controller;

import com.example.alioguzhancicek.pokemonapi.controller.request.CreateFavoriteListRequest;
import com.example.alioguzhancicek.pokemonapi.controller.request.GetPokemonTypesRequest;
import com.example.alioguzhancicek.pokemonapi.controller.request.GetPokemonsRequest;
import com.example.alioguzhancicek.pokemonapi.controller.response.BaseResponse;
import com.example.alioguzhancicek.pokemonapi.controller.response.PokemonDetailResponse;
import com.example.alioguzhancicek.pokemonapi.controller.response.PokemonListResponse;
import com.example.alioguzhancicek.pokemonapi.controller.response.PokemonTypeResponse;
import com.example.alioguzhancicek.pokemonapi.service.PokemonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pokemon-api")
@Slf4j
public class PokemonController {

    private final PokemonService pokemonService;

    @PostMapping("/type")
    @ResponseBody
    public BaseResponse<List<PokemonTypeResponse>> getPokemonTypes(@RequestBody GetPokemonTypesRequest request) {

        return BaseResponse.<List<PokemonTypeResponse>>builder()
                .data(pokemonService.getPokemonTypes(request))
                .success(true)
                .build();
    }

    @PostMapping("/pokemon")
    @ResponseBody
    public BaseResponse<List<PokemonListResponse>> getAll(@RequestBody GetPokemonsRequest request) {
        return BaseResponse.<List<PokemonListResponse>>builder()
                .data(pokemonService.getAllByType(request))
                .success(true)
                .build();
    }

    @GetMapping("/pokemon/{idOrName}")
    @ResponseBody
    public BaseResponse<PokemonDetailResponse> get(@PathVariable String idOrName) {

        return BaseResponse.<PokemonDetailResponse>builder()
                .data(pokemonService.get(idOrName))
                .success(true)
                .build();
    }

    @PostMapping("/pokemon/favorite-list/create")
    @ResponseBody
    public BaseResponse createFavoriteList(@RequestBody CreateFavoriteListRequest request) {

        pokemonService.createFavoriteList(request);

        return BaseResponse.builder()
                .success(true)
                .build();
    }
}
