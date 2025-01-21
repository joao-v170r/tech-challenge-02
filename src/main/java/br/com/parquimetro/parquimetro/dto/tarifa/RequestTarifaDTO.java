package br.com.parquimetro.parquimetro.dto.tarifa;

import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RequestTarifaDTO(
        @NegativeOrZero
        BigDecimal precoIntervalo,
        @NotNull
        String intervalo
) {
}
