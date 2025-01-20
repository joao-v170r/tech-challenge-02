package br.com.parquimetro.parquimetro.service.parquimetro;

import br.com.parquimetro.parquimetro.dto.ParquimetroDTO;
import br.com.parquimetro.parquimetro.erro.ServiceParquimetroErro;
import br.com.parquimetro.parquimetro.model.Parquimetro;
import br.com.parquimetro.parquimetro.repository.ParquimetroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindParquimetroService extends ParquimetroService {

    @Autowired
    public FindParquimetroService(ParquimetroRepository repository) {
        super(repository);
    }

    public List<ParquimetroDTO> findAll() {
        List<Parquimetro> listParquimetro = repository.findAll();
        return listParquimetro.stream().map(ParquimetroService::toParquimetroDTO).toList();
    }

    public ParquimetroDTO find(Long id) {
        Parquimetro parquimetro = repository.findById(id).orElseThrow(() -> new ServiceParquimetroErro("id parquimetro não existe"));
        return toParquimetroDTO(parquimetro);
    }
}
