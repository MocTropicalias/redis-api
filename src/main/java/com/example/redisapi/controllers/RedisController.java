package com.example.redisapi.controllers;

import com.example.redisapi.models.RedisEntity;
import com.example.redisapi.services.RedisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @Operation(summary = "Incrementa o contador do usuário e retorna o novo valor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contador incrementado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao incrementar o contador")
    })
    @GetMapping("/incr")
    public RedisEntity incrementUserCounter() {
        return redisService.increment();
    }

    @Operation(summary = "Decrementa o contador do usuário e retorna o novo valor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contador decrementado com sucesso"),
            @ApiResponse(responseCode = "406", description = "O contador já está em 0 e não pode ser decrementado"),
            @ApiResponse(responseCode = "500", description = "Erro ao decrementar o contador")
    })
    @GetMapping("/decr")
    public ResponseEntity<?> decrementUserCounter() {
        RedisEntity retorno = redisService.decrement();

        if (retorno == null) {
            return ResponseEntity.status(406).body("O contador já está em 0");
        }

        return ResponseEntity.ok(retorno.toString());
    }

    @Operation(summary = "Obtém o valor atual do contador do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Valor do contador obtido com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao obter o valor do contador")
    })
    @GetMapping("/count")
    public RedisEntity getUserCounter() {
        return redisService.get();
    }

}
