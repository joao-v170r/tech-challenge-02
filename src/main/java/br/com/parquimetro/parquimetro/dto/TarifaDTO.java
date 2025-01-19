package br.com.parquimetro.parquimetro.dto;

import br.com.parquimetro.parquimetro.model.Parquimetro;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Set;

public record TarifaDTO(
        Long id,
        BigDecimal precoIntervalo,
        LocalTime intervalo,
        Set<Parquimetro> parquimetros
) {}
