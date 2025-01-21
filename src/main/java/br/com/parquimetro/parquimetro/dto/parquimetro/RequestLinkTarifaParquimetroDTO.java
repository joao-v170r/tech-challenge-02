package br.com.parquimetro.parquimetro.dto.parquimetro;

import jakarta.validation.constraints.NegativeOrZero;

public record RequestLinkTarifaParquimetroDTO(
        @NegativeOrZero(message = "o id do parquimetro não pode ser negativo ou zero")
        Long parquimetroId,
        @NegativeOrZero(message = "o id da tarifa não pode ser negativo ou zero")
        Long tarifaId
) {
}
