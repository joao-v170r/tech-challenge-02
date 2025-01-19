package br.com.parquimetro.parquimetro.erro;

public class ServiceTarifaErro extends RuntimeException {
    public ServiceTarifaErro(String message) {
        super(message);
    }
}
