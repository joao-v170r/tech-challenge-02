package br.com.parquimetro.parquimetro.dto;

import java.math.BigDecimal;
import java.time.LocalTime;

public record RequestTarifaDTO(
        BigDecimal precoIntervalo,
        LocalTime intervalo
) {
}
