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
        var parquimetro = repository.getReferenceById(id);
        parquimetro.setLatitude(dto.latitude());
        parquimetro.setLongitude(dto.longitude());
        parquimetro.setTolerancia(dto.tolerancia());
        parquimetro.setEnderecoCompleto(dto.enderecoCompleto());
        parquimetro.setStatusParquimetro(dto.statusParquimetro());
        return toParquimetroDTO(repository.save(parquimetro));
    }
}
