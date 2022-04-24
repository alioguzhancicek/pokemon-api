package com.example.alioguzhancicek.pokemonapi.controller;

import com.example.alioguzhancicek.pokemonapi.controller.request.GetPokemonTypesRequest;
import com.example.alioguzhancicek.pokemonapi.controller.request.GetPokemonsRequest;
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
    public List<PokemonTypeResponse> getPokemonTypes(@RequestBody GetPokemonTypesRequest request) {
        return pokemonService.getPokemonTypes(request);
    }

    @PostMapping("/pokemon")
    @ResponseBody
    public List<PokemonListResponse> getAll(@RequestBody GetPokemonsRequest request) {
        return pokemonService.getAllByType(request);
    }

    @GetMapping("/pokemon/{idOrName}")
    @ResponseBody
    public PokemonDetailResponse get(@PathVariable String idOrName) {
        return pokemonService.get(idOrName);
    }
}
