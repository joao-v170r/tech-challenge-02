package br.com.parquimetro.parquimetro.dto.parquimetro;

import br.com.parquimetro.parquimetro.model.context.StatusParquimetro;
import jakarta.validation.constraints.NotBlank;

public record RequestParquimetroDTO(
        @NotBlank(message = "o endereço não pode ser vazio")
        String enderecoCompleto,
        Integer latitude,
        Integer longitude,
        String tolerancia,
        StatusParquimetro statusParquimetro
) {}
