package br.com.parquimetro.parquimetro.dto;

import br.com.parquimetro.parquimetro.model.Sessao;
import br.com.parquimetro.parquimetro.model.context.FormaPagamento;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PagamentoDTO(
        Long id,
        LocalDateTime dtPagamento,
        BigDecimal valorPagamento,
        FormaPagamento formaPagamento
) {
}
