package br.com.parquimetro.parquimetro.service.tarifa;

import br.com.parquimetro.parquimetro.dto.RequestTarifaDTO;
import br.com.parquimetro.parquimetro.dto.TarifaDTO;
import br.com.parquimetro.parquimetro.model.Tarifa;
import br.com.parquimetro.parquimetro.repository.TarifaRepository;

import java.time.LocalTime;

public class TarifaService {

    protected TarifaRepository repository;

    public TarifaService(TarifaRepository repository) {
        this.repository = repository;
    }

    public static TarifaDTO toTarifaDTO(Tarifa tarifa) {
        return new TarifaDTO(
                tarifa.getId(),
                tarifa.getPrecoIntervalo(),
                tarifa.getInvervalo()
        );
    }

    protected Tarifa toEntity(RequestTarifaDTO dto) {
        Tarifa tarifa = new Tarifa();

        tarifa.setPrecoIntervalo(dto.precoIntervalo());
        tarifa.setInvervalo(LocalTime.parse(dto.intervalo()));

        return tarifa;
    }
}
