package com.example.alioguzhancicek.pokemonapi.controller;

import com.example.alioguzhancicek.pokemonapi.model.dto.request.GetPokemonsRequest;
import com.example.alioguzhancicek.pokemonapi.model.dto.response.BaseResponse;
import com.example.alioguzhancicek.pokemonapi.model.dto.response.PokemonDetailResponse;
import com.example.alioguzhancicek.pokemonapi.model.dto.response.PokemonListResponse;
import com.example.alioguzhancicek.pokemonapi.service.PokemonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pokemon-api/pokemon")
@Slf4j
public class PokemonController {

    private final PokemonService pokemonService;

    @PostMapping
    @ResponseBody
    public BaseResponse<List<PokemonListResponse>> getAll(@RequestBody GetPokemonsRequest request) {
        return BaseResponse.<List<PokemonListResponse>>builder()
                .data(pokemonService.getAllByType(request))
                .success(true)
                .build();
    }

    @GetMapping("/{idOrName}")
    @ResponseBody
    public BaseResponse<PokemonDetailResponse> get(@PathVariable String idOrName) {

        return BaseResponse.<PokemonDetailResponse>builder()
                .data(pokemonService.get(idOrName))
                .success(true)
                .build();
    }
}
