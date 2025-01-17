package br.com.parquimetro.parquimetro.model;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @OneToOne(mappedBy = "sessao", cascade = CascadeType.ALL)
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name = "parquimetro_id")
    private Parquimetro parquimetro;

    @Enumerated(EnumType.ORDINAL)
    private StatusSessao statusSessao;


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
