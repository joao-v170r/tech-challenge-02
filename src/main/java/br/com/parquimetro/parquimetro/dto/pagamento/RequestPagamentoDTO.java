package br.com.parquimetro.parquimetro.dto.pagamento;

import br.com.parquimetro.parquimetro.model.context.FormaPagamento;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RequestPagamentoDTO(
        @NotNull
        @Min(1)
        Long sessaoId,
        FormaPagamento formaPagamento
) {}
