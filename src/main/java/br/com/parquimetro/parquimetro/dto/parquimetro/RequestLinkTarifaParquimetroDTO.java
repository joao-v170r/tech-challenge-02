package br.com.parquimetro.parquimetro.dto.parquimetro;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotNull;

public record RequestLinkTarifaParquimetroDTO(
        @NotNull
        @Min(1)
        Long parquimetroId,
        @NotNull
        @Min(1)
        Long tarifaId
) {
}
