package br.com.parquimetro.parquimetro.dto.sessao;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestSessaoDTO(
        @NotBlank(message = "a placa do carro não pode ser vazia")
        String placaCarro,
        @NotNull
        @Min(1)
        Long parquimetroId
) {}