package br.com.parquimetro.parquimetro.erro.validation;

import br.com.parquimetro.parquimetro.erro.StandardError;

import java.util.ArrayList;
import java.util.List;

public class ValidateError extends StandardError {
    private List<ValidationMessage> messages = new ArrayList<>();

    public List<ValidationMessage> getMessages() {
        return messages;
    }

    public void addMessages(String campo, String message) {
        this.messages.add(new ValidationMessage(campo, message));
    }

}
