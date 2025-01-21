package br.com.parquimetro.parquimetro.erro;

import org.springframework.boot.autoconfigure.SpringBootApplication;

public class ServiceTarifaNotFoundErro extends RuntimeException {
    public ServiceTarifaNotFoundErro(String message) {
        super(message);
    }
}
