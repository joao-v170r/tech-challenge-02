package br.com.parquimetro.parquimetro.dto.sessao;

import jakarta.validation.constraints.NotBlank;

public record RequestSessaoDTO(
        @NotBlank(message = "a placa do carro não pode ser vazia")
        String placaCarro,
        Long parquimetroId
) {}