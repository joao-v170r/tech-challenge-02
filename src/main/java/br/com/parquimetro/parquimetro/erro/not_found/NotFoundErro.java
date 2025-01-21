package br.com.parquimetro.parquimetro.erro.not_found;

public class NotFoundErro extends  RuntimeException {
    public NotFoundErro(String message) {
        super(message);
    }
}
