package com.example.alioguzhancicek.pokemonapi.exception;

import com.example.alioguzhancicek.pokemonapi.model.dto.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NoSuchPokemonException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public BaseResponse<Object> onNoSuchPokemonException(NoSuchPokemonException e) {
        log.error(e.getMessage(), e);

        return BaseResponse.builder()
                .success(false)
                .errorMessage(e.getMessage())
                .build();
    }
}
