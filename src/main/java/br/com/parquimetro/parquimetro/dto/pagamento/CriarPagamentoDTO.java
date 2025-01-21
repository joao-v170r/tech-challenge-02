package br.com.parquimetro.parquimetro.dto.pagamento;

import br.com.parquimetro.parquimetro.model.context.FormaPagamento;

public record CriarPagamentoDTO (
        Long sessaoId,
        FormaPagamento formaPagamento
) {}
