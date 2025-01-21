package br.com.parquimetro.parquimetro.model;

import br.com.parquimetro.parquimetro.model.context.StatusParquimetro;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.*;

@Entity
@Table(name="tb_parquimetro")
public class Parquimetro {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "endereco_completo")
    private String enderecoCompleto;
    private Integer latitude;
    private Integer longitude;
    private LocalTime tolerancia;

    @OneToMany(mappedBy = "parquimetro", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonBackReference
    private Set<Sessao> sessoes = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tb_parquimetro_tarifa",
            joinColumns = @JoinColumn(name = "parquimetro_id"),
            inverseJoinColumns = @JoinColumn(name = "tarifa_id")
    )
    @JsonManagedReference
    private Set<Tarifa> tarifas = new HashSet<>();

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status_parquimetro")
    private StatusParquimetro statusParquimetro;

    public Parquimetro() {
    }

    public Parquimetro(Long id, String enderecoCompleto, Integer latitude, Integer longitude, LocalTime tolerancia, Set<Sessao> sessoes, Set<Tarifa> tarifas, StatusParquimetro statusParquimetro) {
        this.id = id;
        this.enderecoCompleto = enderecoCompleto;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tolerancia = tolerancia;
        this.sessoes = sessoes;
        this.tarifas = tarifas;
        this.statusParquimetro = statusParquimetro;
    }

    public LocalTime getTolerancia() {
        return tolerancia;
    }

    public void setTolerancia(LocalTime tolerancia) {
        this.tolerancia = tolerancia;
    }

    public Set<Tarifa> getTarifas() {
        return tarifas;
    }

    public void addTarifa(Tarifa tarifa){
        tarifa.addParquimetro(this);
        this.tarifas.add(tarifa);
    }

    public void removeTarifa(Tarifa tarifa) {
        this.tarifas.remove(tarifa);
        tarifa.removeParquimetro(this);
    }

    public Set<Sessao> getSessoes() {
        return sessoes;
    }

    public void addSessao(Sessao sessao) {
        sessao.setParquimetro(this);
        sessoes.add(sessao);
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
