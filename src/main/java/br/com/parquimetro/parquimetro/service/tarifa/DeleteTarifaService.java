package br.com.parquimetro.parquimetro.service.tarifa;

import br.com.parquimetro.parquimetro.persiste.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DeleteTarifaService extends TarifaService {

    @Autowired
    public DeleteTarifaService(TarifaRepository repository) {
        super(repository);
    }


    public void delete(Long id) {
        repository.deleteById(id);
    }
}
