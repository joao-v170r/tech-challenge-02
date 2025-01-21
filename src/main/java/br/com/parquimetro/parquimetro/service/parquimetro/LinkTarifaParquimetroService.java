package br.com.parquimetro.parquimetro.service.parquimetro;

import br.com.parquimetro.parquimetro.dto.parquimetro.ParquimetroDTO;
import br.com.parquimetro.parquimetro.erro.not_found.ParquimetroNotFoundErro;
import br.com.parquimetro.parquimetro.model.Parquimetro;
import br.com.parquimetro.parquimetro.model.Tarifa;
import br.com.parquimetro.parquimetro.repository.ParquimetroRepository;
import br.com.parquimetro.parquimetro.repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkTarifaParquimetroService extends ParquimetroService {

    TarifaRepository tarifaRepository;

    @Autowired
    public LinkTarifaParquimetroService(ParquimetroRepository repository,  TarifaRepository tarifaRepository) {
        super(repository);
        this.tarifaRepository = tarifaRepository;
    }

    public ParquimetroDTO link(Long parquimetroId, Long tarifaId) {
        Parquimetro parquimetro = repository.findById(parquimetroId)
                .orElseThrow(() -> new ParquimetroNotFoundErro("id parquimetro não encontrado"));
        Tarifa tarifa = tarifaRepository.findById(tarifaId)
                .orElseThrow(() -> new ParquimetroNotFoundErro("id tarifa não encontrado"));

        parquimetro.addTarifa(tarifa);

        return toParquimetroDTO(repository.save(parquimetro));
    }

    public ParquimetroDTO unlink(Long parquimetroId, Long tarifaId) {
        Parquimetro parquimetro = repository.findById(parquimetroId)
                .orElseThrow(() -> new ParquimetroNotFoundErro("id parquimetro não encontrado"));
        Tarifa tarifa = tarifaRepository.findById(tarifaId)
                .orElseThrow(() -> new ParquimetroNotFoundErro("id tarifa não encontrado"));

        parquimetro.removeTarifa(tarifa);

        return toParquimetroDTO(parquimetro);
    }
}
