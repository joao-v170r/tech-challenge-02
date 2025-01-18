package br.com.parquimetro.parquimetro.service.parquimetro;

import br.com.parquimetro.parquimetro.dto.ParquimetroDTO;
import br.com.parquimetro.parquimetro.dto.RequestParquimetroDTO;
import br.com.parquimetro.parquimetro.model.Parquimetro;
import br.com.parquimetro.parquimetro.persiste.ParquimetroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveParquimetroService extends ParquimetroService {

    @Autowired
    public SaveParquimetroService(ParquimetroRepository repository) {
        super(repository);
    }

    public ParquimetroDTO save(RequestParquimetroDTO dto) {
        Parquimetro newParquimetro = toEntity(dto);
        return toParquimetroDTO(repository.save(newParquimetro));
    }
}
