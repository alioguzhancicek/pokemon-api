package com.example.alioguzhancicek.pokemonapi.model.dto.request;

import lombok.Data;

import java.util.Map;

@Data
public abstract class FilterableAndSortableAbstractRequest extends SortableAbstractRequest {
    protected Map<String, String> filterMap;
}
