package br.com.parquimetro.parquimetro.persiste;

import br.com.parquimetro.parquimetro.model.Parquimetro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParquimetroRepository extends JpaRepository<Parquimetro, Long> {
}
