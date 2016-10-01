package cashregister.domain.values;

import cashregister.domain.Constraint;

public class ValidationError {
    private final String property;
    private final Constraint constraint;

    public ValidationError(String property, Constraint constraint) {
        this.property = property;
        this.constraint = constraint;
    }

    @Override
    public boolean equals(Object obj) {
        if (!obj.getClass().equals(getClass())){
            return false;
        }

        ValidationError error = (ValidationError) obj;

        return error.property.equals(property) && error.constraint.equals(constraint);
    }
}
