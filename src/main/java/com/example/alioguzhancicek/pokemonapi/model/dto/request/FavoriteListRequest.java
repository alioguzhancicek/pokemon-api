package com.example.alioguzhancicek.pokemonapi.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
public class FavoriteListRequest {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotEmpty
    private List<String> pokemons;
}
