package br.com.parquimetro.parquimetro.model;

import javax.annotation.processing.Generated;

import br.com.parquimetro.parquimetro.model.context.StatusParquimetro;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="tb_parquimetro")
public class Parquimetro {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String enderecoCompleto;
    private Integer latitude;
    private Integer longitude;
    private LocalTime tolerancia;

    @OneToMany(mappedBy = "parquimetro", cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<Sessao> sessoes = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "tb_parquimetro_tarifa",
            joinColumns = @JoinColumn(name = "parquimetro_id"),
            inverseJoinColumns = @JoinColumn(name = "tarifa_id")
    )
    private Set<Tarifa> tarifas = new HashSet<>();

    @Enumerated(EnumType.ORDINAL)
    private StatusParquimetro statusParquimetro;

    public LocalTime getTolerancia() {
        return tolerancia;
    }

    public void setTolerancia(LocalTime tolerancia) {
        this.tolerancia = tolerancia;
    }

    public Set<Sessao> getSessoes() {
        return sessoes;
    }

    public void setSessoes(Set<Sessao> sessoes) {
        this.sessoes = sessoes;
    }

    public Set<Tarifa> getTarifas() {
        return tarifas;
    }

    public void setTarifas(Set<Tarifa> tarifas) {
        this.tarifas = tarifas;
    }

    public void addTarifa(Tarifa tarifa){
        this.tarifas.add(tarifa);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnderecoCompleto() {
        return enderecoCompleto;
    }

    public void setEnderecoCompleto(String enderecoCompleto) {
        this.enderecoCompleto = enderecoCompleto;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public StatusParquimetro getStatusParquimetro() {
        return statusParquimetro;
    }

    public void setStatusParquimetro(StatusParquimetro statusParquimetro) {
        this.statusParquimetro = statusParquimetro;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Parquimetro that = (Parquimetro) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Parquimetro{" +
                "id=" + id +
                ", enderecoCompleto='" + enderecoCompleto + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", tolerancia=" + tolerancia +
                ", sessoes=" + sessoes +
                ", tarifas=" + tarifas +
                ", statusParquimetro=" + statusParquimetro +
                '}';
    }

}
