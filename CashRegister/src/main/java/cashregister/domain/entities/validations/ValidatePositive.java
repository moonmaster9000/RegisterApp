package cashregister.domain.entities.validations;

import cashregister.domain.Constraint;
import cashregister.domain.entities.Entity;
import cashregister.domain.values.ValidationError;

import java.util.List;

public class ValidatePositive extends ValidatePropertyWithConstraint {
    public ValidatePositive(List<ValidationError> errors, String property, Constraint constraint, Entity entity) {
        super(errors, property, constraint, entity);
    }

    protected boolean isValid() {
        return (int) getPropertyValue() > 0;
    }
}
