package br.com.parquimetro.parquimetro.dto;

import br.com.parquimetro.parquimetro.model.Sessao;
import br.com.parquimetro.parquimetro.model.Tarifa;
import br.com.parquimetro.parquimetro.model.context.StatusParquimetro;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Set;

public record ParquimetroDTO(
    Long id,
    String enderecoCompleto,
    Integer latitude,
    Integer longitude,
    LocalTime tolerancia,
    Set<Sessao> sessoes,
    Set<TarifaDTO> tarifas,
    StatusParquimetro statusParquimetro
) {

}
