package cashregister.domain.entities;

import cashregister.domain.Constraint;
import cashregister.domain.values.ValidationError;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;
import java.util.stream.Collectors;

import static cashregister.domain.Constraint.*;
import static javax.validation.Validation.*;

public abstract class Entity {
    private static Map<String,Constraint> annotationToConstraint = new HashMap<>();

    static {
        annotationToConstraint.put("NotNull", REQUIRED);
        annotationToConstraint.put("Min", POSITIVE);
    }

    private String id;

    private Validator validator = buildDefaultValidatorFactory().getValidator();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isValid() {
        return getValidationErrors().isEmpty();
    }

    public List<ValidationError> getValidationErrors() {
        Set<ConstraintViolation<Entity>> errors = validator.validate(this);

        return errors
            .stream()
            .map(this::convertConstraintViolationToValidationError)
            .collect(Collectors.toList());
    }

    private ValidationError convertConstraintViolationToValidationError(ConstraintViolation error) {
        String property = error.getPropertyPath().toString();
        Constraint constraint = annotationToConstraint.get(getConstraintAnnotationName(error));

        return new ValidationError(property, constraint);
    }

    private String getConstraintAnnotationName(ConstraintViolation error) {
        String[] constraintNameParts = error.getConstraintDescriptor().getAnnotation().annotationType().getTypeName().split("\\.");
        return constraintNameParts[constraintNameParts.length - 1];
    }
}
