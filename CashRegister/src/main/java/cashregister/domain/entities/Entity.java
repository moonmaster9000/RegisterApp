package cashregister.domain.entities;

import cashregister.domain.Constraint;
import cashregister.domain.entities.validations.ValidatePropertyWithConstraint;
import cashregister.domain.entities.validations.runtimeExceptions.*;
import cashregister.domain.values.ValidationError;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Entity {
    protected String id;
    protected static final Map<String, Constraint> validations = new HashMap<>();

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
        ArrayList<ValidationError> errors = new ArrayList<>();

        validations.forEach((property, constraint) -> {
            getConstraintValidator(errors, property, constraint).validate();
        });

        return errors;
    }

    private ValidatePropertyWithConstraint getConstraintValidator(ArrayList<ValidationError> errors, String property, Constraint constraint){
        String constraintValidatorClassName = "cashregister.domain.entities.validations.Validate" + constraint.toCapitalizedString();

        try {
            return (ValidatePropertyWithConstraint) Class.
                forName(constraintValidatorClassName).
                getConstructor(List.class, String.class, Constraint.class, Entity.class).
                newInstance(errors, property, constraint, this);

        } catch (InstantiationException e) {
            throw new CantInstantiateValidateClass(constraintValidatorClassName, e);
        } catch (IllegalAccessException e) {
            throw new CantAccessValidateClass(constraintValidatorClassName, e);
        } catch (ClassNotFoundException e) {
            throw new CantFindValidateClass(constraintValidatorClassName, e);
        } catch (NoSuchMethodException e) {
            throw new CantCallMethodOnValidateClass(constraintValidatorClassName, e);
        } catch (InvocationTargetException e) {
            throw new ValidateClassThrewException(constraintValidatorClassName, e);
        }
    }
}
