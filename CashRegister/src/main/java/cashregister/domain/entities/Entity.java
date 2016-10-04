package cashregister.domain.entities;

import cashregister.domain.Constraint;
import cashregister.domain.values.ValidationError;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static cashregister.domain.Constraint.*;

public abstract class Entity {
    protected Object id;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public boolean isValid() {
        return getValidationErrors().isEmpty();
    }

    public List<ValidationError> getValidationErrors() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        validator.validate(this).stream().map(error -> new ValidationError(error.getPropertyPath().toString(), REQUIRED));

        Set<ConstraintViolation<Entity>> errors = validator.validate(this);

        return errors.stream().map((ConstraintViolation error) ->  {
            String property = error.getPropertyPath().toString();
            String[] constraintNameParts = error.getConstraintDescriptor().getAnnotation().annotationType().getTypeName().split("\\.");
            String constraintAnnotation = constraintNameParts[constraintNameParts.length - 1];
            Constraint constraint = null;

            switch (constraintAnnotation) {
                case "NotBlank":
                    constraint = REQUIRED;
                    break;
                case "Min":
                    constraint = POSITIVE;
                    break;
            }

            return new ValidationError(property, constraint);
        }).collect(Collectors.toList());
    }
}
