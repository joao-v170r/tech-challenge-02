package br.com.parquimetro.parquimetro.service.parquimetro;

import br.com.parquimetro.parquimetro.dto.ParquimetroDTO;
import br.com.parquimetro.parquimetro.dto.RequestParquimetroDTO;
import br.com.parquimetro.parquimetro.erro.ServiceParquimetroErro;
import br.com.parquimetro.parquimetro.model.Parquimetro;
import br.com.parquimetro.parquimetro.persiste.ParquimetroRepository;

import java.util.List;

public abstract class ParquimetroService {

    protected ParquimetroRepository repository;

    public ParquimetroService(ParquimetroRepository repository) {
        this.repository = repository;
    }

    public List<ParquimetroDTO> findAll() {
        List<Parquimetro> listParquimetro = repository.findAll();
        return listParquimetro.stream().map(this::toParquimetroDTO).toList();
    }

    public ParquimetroDTO find(Long id) {
        Parquimetro parquimetro = repository.findById(id).orElseThrow(() -> new ServiceParquimetroErro("id parquimetro não existe"));
        return toParquimetroDTO(parquimetro);
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
        parquimetro.setStatusParquimetro(dto.statusParquimetro());

        return parquimetro;
    }
}
