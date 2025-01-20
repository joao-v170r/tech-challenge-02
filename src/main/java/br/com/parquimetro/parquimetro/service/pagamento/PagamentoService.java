package br.com.parquimetro.parquimetro.service.pagamento;

import br.com.parquimetro.parquimetro.dto.PagamentoDTO;
import br.com.parquimetro.parquimetro.model.Pagamento;

public class PagamentoService {
    public static PagamentoDTO toPagamentoDTO(Pagamento pagamento) {
        return new PagamentoDTO(
                pagamento.getId(),
                pagamento.getDtPagamento(),
                pagamento.getValorPagamento(),
                pagamento.getFormaPagamento()
        );
    }
}
