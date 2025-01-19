package br.com.parquimetro.parquimetro.persiste;

import br.com.parquimetro.parquimetro.model.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarifaRepository extends JpaRepository<Tarifa, Long> {
}
