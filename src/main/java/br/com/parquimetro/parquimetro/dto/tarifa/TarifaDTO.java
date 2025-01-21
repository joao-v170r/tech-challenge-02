package br.com.parquimetro.parquimetro.dto.tarifa;

import java.math.BigDecimal;
import java.time.LocalTime;

public record TarifaDTO(
        Long id,
        BigDecimal precoIntervalo,
        LocalTime intervalo
) {}
