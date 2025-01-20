package br.com.parquimetro.parquimetro.persiste;

import br.com.parquimetro.parquimetro.model.Sessao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessaoRepository extends JpaRepository<Sessao, Long> {

    Page<Sessao> findByParquimetroId(Long parquimetroId, Pageable pageable);
}
