package br.com.parquimetro.parquimetro.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import br.com.parquimetro.parquimetro.model.context.StatusSessao;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_sessao")
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String placaCarro;
    private LocalDateTime dtEntrada;
    private LocalDateTime dtSaida;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pagamento_id", referencedColumnName = "id")
    private Pagamento pagamento;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parquimetro_id")
    private Parquimetro parquimetro;

    @Enumerated(EnumType.ORDINAL)
    private StatusSessao statusSessao;

    public Sessao() {}

    public Sessao(String placaCarro, Parquimetro parquimetro) {
        this.placaCarro = placaCarro;
        this.dtEntrada = LocalDateTime.now();
        this.parquimetro = parquimetro;
        this.statusSessao = StatusSessao.EM_ANDAMENTO;
    }

    public StatusSessao getStatusSessao() {
        return statusSessao;
    }

    public void setStatusSessao(StatusSessao statusSessao) {
        this.statusSessao = statusSessao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlacaCarro() {
        return placaCarro;
    }

    public void setPlacaCarro(String placaCarro) {
        this.placaCarro = placaCarro;
    }

    public LocalDateTime getDtEntrada() {
        return dtEntrada;
    }

    public void setDtEntrada(LocalDateTime dtEntrada) {
        this.dtEntrada = dtEntrada;
    }

    public LocalDateTime getDtSaida() {
        return dtSaida;
    }

    public void setDtSaida(LocalDateTime dtSaida) {
        this.dtSaida = dtSaida;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Parquimetro getParquimetro() {
        return parquimetro;
    }

    public void setParquimetro(Parquimetro parquimetro) {
        this.parquimetro = parquimetro;
    }

    public BigDecimal getCustoSessao() {
        Duration tempoDecorrido = Duration.between(this.getDtEntrada(), this.getDtSaida());
        Set<Tarifa> tarifas = parquimetro.getTarifas();
        Tarifa tarifaMaisProxima = null;
        long diferencaMinima = Long.MAX_VALUE;
        for (Tarifa tarifa : tarifas) {
            Duration intervaloTarifa = Duration.ofHours(tarifa.getInvervalo().getHour())
                    .plusMinutes(tarifa.getInvervalo().getMinute());
            long diferencaAtual = Math.abs(tempoDecorrido.toMinutes() - intervaloTarifa.toMinutes());
            if (diferencaAtual < diferencaMinima) {
                diferencaMinima = diferencaAtual;
                tarifaMaisProxima = tarifa;
            }
        }
        BigDecimal horas = BigDecimal.valueOf(tempoDecorrido.toHours());
        return tarifaMaisProxima != null ? tarifaMaisProxima.getPrecoIntervalo().multiply(horas) : BigDecimal.ZERO;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Sessao sessao = (Sessao) o;
        return Objects.equals(id, sessao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Sessao{" +
                "id=" + id +
                ", placaCarro='" + placaCarro + '\'' +
                ", dtEntrada=" + dtEntrada +
                ", dtSaida=" + dtSaida +
                ", pagamento=" + pagamento +
                ", parquimetro=" + parquimetro +
                ", statusSessao=" + statusSessao +
                '}';
    }
}
