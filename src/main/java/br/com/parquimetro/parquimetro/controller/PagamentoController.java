package br.com.parquimetro.parquimetro.controller;

import br.com.parquimetro.parquimetro.dto.pagamento.RequestPagamentoDTO;
import br.com.parquimetro.parquimetro.dto.pagamento.PagamentoDTO;
import br.com.parquimetro.parquimetro.service.pagamento.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamento")
@Tag(name = "Pagamento", description = "Endpoints de pagamento da sessão.")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping("/init")
    @Operation(
            summary = "Cria um pagamento.",
            description = "O motorista fez o pagamento de uma sessão."
    )
    public ResponseEntity<PagamentoDTO> save(@Valid @RequestBody RequestPagamentoDTO requestPagamentoDTO) {
        pagamentoService.save(requestPagamentoDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(pagamentoService.save(requestPagamentoDTO));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Alterar um pagamento.",
            description = "Caso o motorista fique muito tempo estacionado após fazer um pagamento, " +
                    "pode ser necessário atualizar o seu valor. "
    )
    public ResponseEntity<PagamentoDTO> update(@PathVariable Long id) {
        PagamentoDTO pagamentoDTOUpdated = pagamentoService.update(id);
        return ResponseEntity.ok(pagamentoDTOUpdated);
    }

}
