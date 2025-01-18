package br.com.parquimetro.parquimetro.service.parquimetro;

import br.com.parquimetro.parquimetro.dto.ParquimetroDTO;
import br.com.parquimetro.parquimetro.dto.RequestParquimetroDTO;
import br.com.parquimetro.parquimetro.persiste.ParquimetroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateParquimetroService extends ParquimetroService {

    @Autowired
    public UpdateParquimetroService(ParquimetroRepository repository) {
        super(repository);
    }

    public ParquimetroDTO update(Long id, RequestParquimetroDTO dto) {
        var lastParquimetro = repository.getReferenceById(id);
        lastParquimetro.setLatitude(dto.latitude());
        lastParquimetro.setLongitude(dto.longitude());
        lastParquimetro.setTolerancia(dto.tolerancia());
        lastParquimetro.setEnderecoCompleto(dto.enderecoCompleto());
        lastParquimetro.setStatusParquimetro(dto.statusParquimetro());
        return toParquimetroDTO(lastParquimetro);
    }
}
