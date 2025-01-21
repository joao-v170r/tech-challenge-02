package br.com.parquimetro.parquimetro.repository;

import br.com.parquimetro.parquimetro.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Sessao, Long> {
}
