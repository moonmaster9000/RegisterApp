package cashregister.domain.entities.validations;

import cashregister.domain.Constraint;
import cashregister.domain.entities.Entity;
import cashregister.domain.values.ValidationError;

import java.util.List;

public class ValidateRequired extends ValidatePropertyWithConstraint {
    public ValidateRequired(List<ValidationError> errors, String property, Constraint constraint, Entity entity) {
        super(errors, property, constraint, entity);
    }

    protected boolean isValid() {
        return getPropertyValue() != null;
    }
}
