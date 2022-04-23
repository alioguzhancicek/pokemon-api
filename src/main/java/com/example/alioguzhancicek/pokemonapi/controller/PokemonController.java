package com.example.alioguzhancicek.pokemonapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pokemon-api")
@Slf4j
public class PokemonController {

    @GetMapping("/deneme")
    @ResponseBody
    public String deneme() {
        return "basarili deneme";
    }
}
