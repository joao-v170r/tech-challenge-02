package br.com.parquimetro.parquimetro.util;

import br.com.parquimetro.parquimetro.model.Sessao;
import br.com.parquimetro.parquimetro.model.Tarifa;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

public class CustoSessao {

    public static Duration getTempoDecorridoSessao(Sessao sessao) {
        return Duration.between(sessao.getDtEntrada(), LocalDateTime.now());
    }

    public static BigDecimal getCustoSessao(Sessao sessao) {
        Duration tempoDecorrido = getTempoDecorridoSessao(sessao);
        LocalTime tolerancia = sessao.getParquimetro().getTolerancia();
        // Dentro da tolerância fica pagamento de valor zero.
        if (tempoDecorrido.compareTo(converterParaDuration(tolerancia)) <= 0) {
            return new BigDecimal("0");
        }
        Set<Tarifa> tarifas = sessao.getParquimetro().getTarifas();
        if (tarifas == null || tarifas.isEmpty()) {
            throw new IllegalArgumentException("Não há tarifas cadastradas.");
        }
        // Ordernar tarifas por intervalo
        TreeSet<Tarifa> tarifasOrdenadas = new TreeSet<>(Comparator.comparing(
                tarifa -> converterParaDuration(tarifa.getInvervalo())));
        tarifasOrdenadas.addAll(tarifas);
        // Menor tarifa maior que o tempo decorrido
        Optional<Tarifa> tarifaPagamento = tarifasOrdenadas.stream()
                .filter(tarifa -> {
                    return converterParaDuration(tarifa.getInvervalo()).compareTo(tempoDecorrido) > 0;
                })
                .findFirst();
        // Caso não existir tarifa maior, pega a maior tarifa
        Tarifa tarifaEscolhida = tarifaPagamento.orElse(tarifasOrdenadas.last());
        return tarifaEscolhida.getPrecoIntervalo();
    }

    public static Duration converterParaDuration(LocalTime localTime) {
        return Duration.ofHours(localTime.getHour())
                .plusMinutes(localTime.getMinute());
    }
}
