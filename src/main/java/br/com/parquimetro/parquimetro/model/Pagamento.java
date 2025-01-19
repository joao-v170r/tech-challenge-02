package br.com.parquimetro.parquimetro.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import br.com.parquimetro.parquimetro.model.context.FormaPagamento;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "pagamento", cascade = CascadeType.ALL)
    private Sessao sessao;

    private LocalDateTime dtPagamento;
    private BigDecimal valorPagamento;

    @Enumerated(EnumType.ORDINAL)
    private FormaPagamento formaPagamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public LocalDateTime getDtPagamento() {
        return dtPagamento;
    }

    public void setDtPagamento(LocalDateTime dtPagamento) {
        this.dtPagamento = dtPagamento;
    }

    public BigDecimal getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(BigDecimal valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(id, pagamento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "id=" + id +
                ", sessao=" + sessao +
                ", dtPagamento=" + dtPagamento +
                ", valorPagamento=" + valorPagamento +
                ", formaPagamento=" + formaPagamento +
                '}';
    }
}
