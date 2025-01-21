package br.com.parquimetro.parquimetro.service.tarifa;

import br.com.parquimetro.parquimetro.erro.not_found.TarifaNotFoundErro;
import br.com.parquimetro.parquimetro.model.Parquimetro;
import br.com.parquimetro.parquimetro.model.Tarifa;
import br.com.parquimetro.parquimetro.repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteTarifaService extends TarifaService {

    @Autowired
    public DeleteTarifaService(TarifaRepository repository) {
        super(repository);
    }

    public void delete(Long id) {
        Tarifa tarifa = repository.findById(id)
                .orElseThrow(() -> new TarifaNotFoundErro("Tarifa não encontrada"));

        // Remove associações na tabela intermediária
        for (Parquimetro parquimetro : tarifa.getParquimetros()) {
            parquimetro.removeTarifa(tarifa);
        }

        tarifa.setParquimetros(null);

        repository.deleteById(id);
    }
}
