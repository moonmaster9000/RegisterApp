package api.controllers;

import cashregister.domain.usecases.exceptions.InvalidRequest;
import cashregister.domain.values.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public class UseCaseController {
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(InvalidRequest.class)
    public List<ValidationError> handleInvalidRequest(InvalidRequest invalidRequest) {
        return invalidRequest.getErrors();
    }
}
