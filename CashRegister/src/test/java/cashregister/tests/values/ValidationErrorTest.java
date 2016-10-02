package cashregister.tests.values;

import cashregister.domain.Constraint;
import cashregister.domain.values.ValidationError;
import org.junit.Test;

import static cashregister.domain.Constraint.*;
import static org.junit.Assert.*;

public class ValidationErrorTest {

    private String prop = "some property";
    private Constraint constraint = REQUIRED;

    @Test
    public void equalWhenPropertiesAndConstraintsEqual() {
        ValidationError e1 = new ValidationError(prop, constraint);
        ValidationError e2 = new ValidationError(prop, constraint);

        assertEquals(e1, e2);
    }

    @Test
    public void notEqualWhenPropertiesDiffer() {
        ValidationError e1 = new ValidationError("prop1", constraint);
        ValidationError e2 = new ValidationError("prop2", constraint);

        assertNotEquals(e1, e2);
    }

    @Test
    public void notEqualWhenConstraintsDiffer() {
        ValidationError e1 = new ValidationError(prop, REQUIRED);
        ValidationError e2 = new ValidationError(prop, UNIQUE);

        assertNotEquals(e1, e2);
    }

    @Test
    public void notEqualWhenObjectsDiffer() {
        ValidationError e1 = new ValidationError(prop, REQUIRED);

        assertNotEquals(e1, "not a validation error");
    }
}