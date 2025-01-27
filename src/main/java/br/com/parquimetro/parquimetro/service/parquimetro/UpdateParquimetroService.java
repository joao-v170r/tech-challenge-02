package br.com.parquimetro.parquimetro.service.parquimetro;

import br.com.parquimetro.parquimetro.dto.parquimetro.ParquimetroDTO;
import br.com.parquimetro.parquimetro.dto.parquimetro.RequestParquimetroDTO;
import br.com.parquimetro.parquimetro.erro.not_found.ParquimetroNotFoundErro;
import br.com.parquimetro.parquimetro.repository.ParquimetroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class UpdateParquimetroService extends ParquimetroService {

    @Autowired
    public UpdateParquimetroService(ParquimetroRepository repository) {
        super(repository);
    }

    public ParquimetroDTO update(Long id, RequestParquimetroDTO dto) {
        try {
            var parquimetro = repository.getReferenceById(id);
            parquimetro.setLatitude(dto.latitude());
            parquimetro.setLongitude(dto.longitude());
            parquimetro.setTolerancia(LocalTime.parse(dto.tolerancia()));
            parquimetro.setEnderecoCompleto(dto.enderecoCompleto());
            parquimetro.setStatusParquimetro(dto.statusParquimetro());
            return toParquimetroDTO(repository.save(parquimetro));
        } catch (EntityNotFoundException e) {
            throw new ParquimetroNotFoundErro("parquimetro não encontrado");
        }
    }
}
