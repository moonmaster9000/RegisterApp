package cashregister.domain.values;

import cashregister.domain.Constraint;

public class ValidationError {
    private final String displayName;
    private final Constraint required;

    public ValidationError(String displayName, Constraint required) {
        this.displayName = displayName;
        this.required = required;
    }
}
