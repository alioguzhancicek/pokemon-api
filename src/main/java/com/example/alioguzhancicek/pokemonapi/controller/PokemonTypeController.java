package com.example.alioguzhancicek.pokemonapi.controller;

import com.example.alioguzhancicek.pokemonapi.model.dto.request.GetPokemonTypesRequest;
import com.example.alioguzhancicek.pokemonapi.model.dto.response.BaseResponse;
import com.example.alioguzhancicek.pokemonapi.model.dto.response.PokemonTypeResponse;
import com.example.alioguzhancicek.pokemonapi.service.PokemonTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pokemon-api/pokemon/type")
@Slf4j
public class PokemonTypeController {

    private final PokemonTypeService pokemonTypeService;

    @PostMapping
    @ResponseBody
    public BaseResponse<List<PokemonTypeResponse>> getAll(@RequestBody GetPokemonTypesRequest request) {

        return BaseResponse.<List<PokemonTypeResponse>>builder()
                .data(pokemonTypeService.findAll(request))
                .success(true)
                .build();
    }
}
