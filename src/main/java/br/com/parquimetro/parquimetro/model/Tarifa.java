package br.com.parquimetro.parquimetro.model;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToMany;

@Entity
@Table(name="tb_tarifa")
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    BigDecimal precoIntervalo;
    LocalTime  invervalo;

    @ManyToMany(mappedBy = "tarifas")
    List<Parquimetro> parquimetros = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrecoIntervalo() {
        return precoIntervalo;
    }

    public void setPrecoIntervalo(BigDecimal precoIntervalo) {
        this.precoIntervalo = precoIntervalo;
    }

    public LocalTime getInvervalo() {
        return invervalo;
    }

    public void setInvervalo(LocalTime invervalo) {
        this.invervalo = invervalo;
    }

    public List<Parquimetro> getParquimetros() {
        return parquimetros;
    }

    public void setParquimetros(List<Parquimetro> parquimetros) {
        this.parquimetros = parquimetros;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tarifa tarifa = (Tarifa) o;
        return Objects.equals(id, tarifa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Tarifa{" +
                "id=" + id +
                ", precoIntervalo=" + precoIntervalo +
                ", invervalo=" + invervalo +
                ", parquimetros=" + parquimetros +
                '}';
    }
}
