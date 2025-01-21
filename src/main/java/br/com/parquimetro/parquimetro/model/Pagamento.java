package br.com.parquimetro.parquimetro.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import br.com.parquimetro.parquimetro.model.context.FormaPagamento;
import br.com.parquimetro.parquimetro.model.context.StatusSessao;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "pagamento", cascade = CascadeType.ALL)
    @JsonBackReference
    private Sessao sessao;

    @Column(name = "dt_pagamento")
    private LocalDateTime dtPagamento;

    @Column(name = "valor_pagamento")
    private BigDecimal valorPagamento;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "forma_pagamento")
    private FormaPagamento formaPagamento;

    public Pagamento() {}

    public Pagamento(Sessao sessao, BigDecimal valorPagamento, FormaPagamento formaPagamento) {
        this.sessao = sessao;
        this.dtPagamento = LocalDateTime.now();
        this.valorPagamento = valorPagamento;
        if (Objects.equals(valorPagamento, BigDecimal.ZERO)) {
            formaPagamento = FormaPagamento.SEM_PAGAMENTO;
        }
        this.formaPagamento = formaPagamento;
    }

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
