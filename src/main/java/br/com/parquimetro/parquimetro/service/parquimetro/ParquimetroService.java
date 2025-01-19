package br.com.parquimetro.parquimetro.service.parquimetro;

import br.com.parquimetro.parquimetro.dto.ParquimetroDTO;
import br.com.parquimetro.parquimetro.dto.RequestParquimetroDTO;
import br.com.parquimetro.parquimetro.model.Parquimetro;
import br.com.parquimetro.parquimetro.persiste.ParquimetroRepository;


public abstract class ParquimetroService {

    protected ParquimetroRepository repository;

    public ParquimetroService(ParquimetroRepository repository) {
        this.repository = repository;
    }

    protected ParquimetroDTO toParquimetroDTO(Parquimetro parquimetro) {
        return new ParquimetroDTO(
                parquimetro.getId(),
                parquimetro.getEnderecoCompleto(),
                parquimetro.getLatitude(),
                parquimetro.getLongitude(),
                parquimetro.getTolerancia(),
                parquimetro.getSessoes(),
                parquimetro.getTarifas(),
                parquimetro.getStatusParquimetro()
        );
    }

    protected Parquimetro toEntity(RequestParquimetroDTO dto) {
        Parquimetro parquimetro = new Parquimetro();

        parquimetro.setEnderecoCompleto(dto.enderecoCompleto());
        parquimetro.setLatitude(dto.latitude());
        parquimetro.setLongitude(dto.longitude());
        parquimetro.setTolerancia(dto.tolerancia());
        parquimetro.setStatusParquimetro(dto.statusParquimetro());

        return parquimetro;
    }
}
