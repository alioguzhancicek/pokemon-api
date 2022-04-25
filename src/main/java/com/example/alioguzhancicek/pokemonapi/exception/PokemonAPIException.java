package com.example.alioguzhancicek.pokemonapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
public class PokemonAPIException extends RuntimeException {
    private String message;
}
