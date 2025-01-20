package br.com.parquimetro.parquimetro.dto.sessao;

import br.com.parquimetro.parquimetro.dto.PagamentoDTO;
import br.com.parquimetro.parquimetro.dto.ParquimetroDTO;
import br.com.parquimetro.parquimetro.model.context.StatusSessao;

import java.time.LocalDateTime;

public record SessaoDTO(
        Long id,
        String placaCarro,
        LocalDateTime dtEntrada,
        LocalDateTime dtSaida,
        PagamentoDTO pagamento,
        ParquimetroDTO parquimetro,
        StatusSessao statusSessao
) {
}