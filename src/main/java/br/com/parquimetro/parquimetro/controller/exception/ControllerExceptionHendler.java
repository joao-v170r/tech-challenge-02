package br.com.parquimetro.parquimetro.controller.exception;

import br.com.parquimetro.parquimetro.erro.ServiceNotFoundErro;
import br.com.parquimetro.parquimetro.erro.StandardError;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@Hidden
@RestControllerAdvice
public class ControllerExceptionHendler {
    private StandardError err = new StandardError();

    @ExceptionHandler(ServiceNotFoundErro.class)
    public ResponseEntity<StandardError> entityNotFound(
            ServiceNotFoundErro e,
            HttpServletRequest req
    ) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        err.setTimeInstant(Instant.now());
        err.setStatus(status.value());
        err.setError("Entity not found");
        err.setMessage(e.getMessage());
        err.setPath(req.getRequestURI());

        return ResponseEntity.status(status).body(this.err);
    }
}
