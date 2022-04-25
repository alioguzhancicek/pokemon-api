package com.example.alioguzhancicek.pokemonapi.model.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {

    public static final BaseResponse<Object> SUCCESS_RESPONSE = BaseResponse.builder().success(true).build();

    @NotNull
    @Builder.Default
    private boolean success = true;

    private String errorMessage;

    private T data;
}
