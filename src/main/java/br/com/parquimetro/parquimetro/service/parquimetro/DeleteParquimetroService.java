package br.com.parquimetro.parquimetro.service.parquimetro;

import br.com.parquimetro.parquimetro.repository.ParquimetroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteParquimetroService extends ParquimetroService {

    @Autowired
    public DeleteParquimetroService(ParquimetroRepository repository) {
        super(repository);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
