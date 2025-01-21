package br.com.parquimetro.parquimetro.model;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="tb_tarifa")
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "preco_intervalo")
    BigDecimal precoIntervalo;
    @Column(name = "intervalo")
    LocalTime  invervalo;

    @ManyToMany(mappedBy = "tarifas", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JsonBackReference
    Set<Parquimetro> parquimetros = new HashSet<>();

    public void addParquimetro(Parquimetro parquimetro) {
        parquimetros.add(parquimetro);
    }

    public void removeParquimetro(Parquimetro parquimetro) {
        parquimetros.remove(parquimetro);
    }

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

    public Set<Parquimetro> getParquimetros() {
        return parquimetros;
    }

    public void setParquimetros(Set<Parquimetro> parquimetros) {
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
