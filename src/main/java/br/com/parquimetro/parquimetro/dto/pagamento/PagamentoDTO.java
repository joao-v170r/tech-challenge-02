package br.com.parquimetro.parquimetro.dto.pagamento;

import br.com.parquimetro.parquimetro.model.context.FormaPagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PagamentoDTO(
        Long id,
        LocalDateTime dtPagamento,
        BigDecimal valorPagamento,
        FormaPagamento formaPagamento
) {
}
