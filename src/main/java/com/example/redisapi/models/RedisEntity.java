package com.example.redisapi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidade que representa um contador armazenado no Redis")
public class RedisEntity {

    @Schema(description = "O contador armazenado no Redis", example = "42")
    private Long count;
}
