package br.com.parquimetro.parquimetro.dto;

import br.com.parquimetro.parquimetro.model.context.StatusParquimetro;

import java.time.LocalTime;

public record RequestParquimetroDTO(
        String enderecoCompleto,
        Integer latitude,
        Integer longitude,
        String tolerancia,
        StatusParquimetro statusParquimetro
) {}
