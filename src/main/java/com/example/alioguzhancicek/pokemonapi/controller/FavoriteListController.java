package com.example.alioguzhancicek.pokemonapi.controller;

import com.example.alioguzhancicek.pokemonapi.model.dto.request.FavoriteListRequest;
import com.example.alioguzhancicek.pokemonapi.model.dto.response.BaseResponse;
import com.example.alioguzhancicek.pokemonapi.service.FavoriteListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pokemon-api/favorite-list")
@Slf4j
public class FavoriteListController {

    private final FavoriteListService favoriteListService;

    @PostMapping
    @ResponseBody
    public BaseResponse create(@RequestBody FavoriteListRequest request) {

        favoriteListService.create(request);

        return BaseResponse.builder()
                .success(true)
                .build();
    }

    @PostMapping("/add")
    @ResponseBody
    public BaseResponse addToFavoriteList(@RequestBody FavoriteListRequest request) {

        favoriteListService.addToFavoriteList(request);

        return BaseResponse.builder()
                .success(true)
                .build();
    }

    @PostMapping("/delete")
    @ResponseBody
    public BaseResponse deleteFromFavoriteList(@RequestBody FavoriteListRequest request) {

        favoriteListService.deleteFromFavoriteList(request);

        return BaseResponse.builder()
                .success(true)
                .build();
    }

    @DeleteMapping("/{name}")
    @ResponseBody
    public BaseResponse deleteFavoriteList(@PathVariable String name) {

        favoriteListService.deleteFavoriteList(name);

        return BaseResponse.builder()
                .success(true)
                .build();
    }
}
