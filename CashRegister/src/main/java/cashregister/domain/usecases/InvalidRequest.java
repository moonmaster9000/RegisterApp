package cashregister.domain.usecases;

import cashregister.domain.values.ValidationError;

import java.util.List;

public class InvalidRequest extends RuntimeException {
    private List<ValidationError> errors;

    public InvalidRequest(List<ValidationError> errors) {
        this.errors = errors;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }
}
