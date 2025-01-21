package br.com.parquimetro.parquimetro.service.tarifa;

import br.com.parquimetro.parquimetro.dto.tarifa.RequestTarifaDTO;
import br.com.parquimetro.parquimetro.dto.tarifa.TarifaDTO;
import br.com.parquimetro.parquimetro.model.Tarifa;
import br.com.parquimetro.parquimetro.repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveTarifaService extends TarifaService{

    @Autowired
    public SaveTarifaService(TarifaRepository repository) {
        super(repository);
    }

    public TarifaDTO save(RequestTarifaDTO dto) {
        Tarifa tarifa = toEntity(dto);
        return toTarifaDTO(repository.save(tarifa));
    }
}
