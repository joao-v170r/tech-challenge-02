package br.com.parquimetro.parquimetro.erro;

public class ServiceParquimetroErro extends RuntimeException {
    public ServiceParquimetroErro(String message) {
        super(message);
    }
}
