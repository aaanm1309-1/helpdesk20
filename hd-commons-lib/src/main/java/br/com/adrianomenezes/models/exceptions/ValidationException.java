package br.com.adrianomenezes.models.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
public class ValidationException extends StandardError {

    @Getter
    private List<FieldError> errors;

    @Getter
    @AllArgsConstructor
    private class FieldError {
        private final String fieldName;
        private final String message;

    }

    public void addError(String fieldName, String message) {
        this.errors.add(new FieldError(fieldName, message));
    }
}
