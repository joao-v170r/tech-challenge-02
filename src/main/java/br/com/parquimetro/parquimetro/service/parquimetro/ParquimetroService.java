package br.com.parquimetro.parquimetro.service.parquimetro;

import br.com.parquimetro.parquimetro.dto.parquimetro.ParquimetroDTO;
import br.com.parquimetro.parquimetro.dto.parquimetro.RequestParquimetroDTO;
import br.com.parquimetro.parquimetro.model.Parquimetro;
import br.com.parquimetro.parquimetro.repository.ParquimetroRepository;
import java.time.LocalTime;


public abstract class ParquimetroService {

    protected ParquimetroRepository repository;

    public ParquimetroService(ParquimetroRepository repository) {
        this.repository = repository;
    }

    public static ParquimetroDTO toParquimetroDTO(Parquimetro parquimetro) {
        return new ParquimetroDTO(
                parquimetro.getId(),
                parquimetro.getEnderecoCompleto(),
                parquimetro.getLatitude(),
                parquimetro.getLongitude(),
                parquimetro.getTolerancia(),
                parquimetro.getTarifas(),
                parquimetro.getStatusParquimetro()
        );
    }

    protected Parquimetro toEntity(RequestParquimetroDTO dto) {
        Parquimetro parquimetro = new Parquimetro();

        parquimetro.setEnderecoCompleto(dto.enderecoCompleto());
        parquimetro.setLatitude(dto.latitude());
        parquimetro.setLongitude(dto.longitude());
        parquimetro.setTolerancia(LocalTime.parse(dto.tolerancia()));
        parquimetro.setStatusParquimetro(dto.statusParquimetro());

        return parquimetro;
    }
}
