package com.example.alioguzhancicek.pokemonapi.model.dto.request;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public abstract class SortableAbstractRequest {
    protected String sortCol;
    protected Sort.Direction sortDir = Sort.Direction.ASC;
}