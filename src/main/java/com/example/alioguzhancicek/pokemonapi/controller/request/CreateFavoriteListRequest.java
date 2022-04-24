package com.example.alioguzhancicek.pokemonapi.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
public class CreateFavoriteListRequest {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotEmpty
    private List<String> favoritePokemons;
}
