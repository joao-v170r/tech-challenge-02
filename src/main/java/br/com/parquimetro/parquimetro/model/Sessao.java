package br.com.parquimetro.parquimetro.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_sessao")
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    LocalDateTime dtEntrada;
    LocalDateTime dtSaida;
    String placa;
    BigDecimal precoSessao;
    @ManyToOne
    @JoinColumn(name = "cotacao_id")
    Cotacao cotacao;

    public Sessao() {
    }

    public Sessao(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public BigDecimal getPrecoSessao() {
        return precoSessao;
    }

    public void setPrecoSessao(BigDecimal precoSessao) {
        this.precoSessao = precoSessao;
    }

    public Cotacao getCotacao() {
        return cotacao;
    }

    public void setCotacao(Cotacao cotacao) {
        this.cotacao = cotacao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((dtEntrada == null) ? 0 : dtEntrada.hashCode());
        result = prime * result + ((dtSaida == null) ? 0 : dtSaida.hashCode());
        result = prime * result + ((placa == null) ? 0 : placa.hashCode());
        result = prime * result + ((precoSessao == null) ? 0 : precoSessao.hashCode());
        result = prime * result + ((cotacao == null) ? 0 : cotacao.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Sessao other = (Sessao) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
       return true;
    }

    @Override
    public String toString() {
        return "Sessao [id=" + id + ", dtEntrada=" + dtEntrada + ", dtSaida=" + dtSaida + ", placa=" + placa
                + ", precoSessao=" + precoSessao + ", cotacao=" + cotacao + "]";
    }
    
    
}
