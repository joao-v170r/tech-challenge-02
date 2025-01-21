package br.com.parquimetro.parquimetro.dto.parquimetro;

import br.com.parquimetro.parquimetro.model.Tarifa;
import br.com.parquimetro.parquimetro.model.context.StatusParquimetro;

import java.time.LocalTime;
import java.util.Set;

public record ParquimetroDTO(
    Long id,
    String enderecoCompleto,
    Integer latitude,
    Integer longitude,
    LocalTime tolerancia,
    Set<Tarifa> tarifas,
    StatusParquimetro statusParquimetro
) {}
