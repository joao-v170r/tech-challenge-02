package br.com.parquimetro.parquimetro.dto.sessao;

import br.com.parquimetro.parquimetro.model.Pagamento;
import br.com.parquimetro.parquimetro.model.Parquimetro;
import br.com.parquimetro.parquimetro.model.context.StatusSessao;

import java.time.LocalDateTime;

public record SessaoDTO(
        Long id,
        String placaCarro,
        LocalDateTime dtEntrada,
        LocalDateTime dtSaida,
        Pagamento pagamento,
        Parquimetro parquimetro,
        StatusSessao statusSessao
) {
}