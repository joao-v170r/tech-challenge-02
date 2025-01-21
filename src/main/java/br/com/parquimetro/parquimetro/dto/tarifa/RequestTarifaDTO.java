package br.com.parquimetro.parquimetro.dto.tarifa;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RequestTarifaDTO(
        BigDecimal precoIntervalo,
        @NotNull
        String intervalo
) {
}
