package api.controllers;

import cashregister.domain.values.ValidationError;

import java.util.List;

public class InvalidRequestException extends RuntimeException {
    private List<ValidationError> errors;

    public InvalidRequestException(List<ValidationError> errors) {
        this.errors = errors;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }
}
