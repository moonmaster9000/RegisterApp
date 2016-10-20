package webFrontend.controllers;

import cashregister.domain.values.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public class UseCaseController {
    public void validationFailed(List<ValidationError> errors) {
        throw new InvalidRequestException(errors);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(InvalidRequestException.class)
    public List<ValidationError> handleInvalidRequest(InvalidRequestException invalidRequest) {
        return invalidRequest.getErrors();
    }
}
