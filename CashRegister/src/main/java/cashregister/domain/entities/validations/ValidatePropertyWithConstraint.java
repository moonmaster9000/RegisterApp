package cashregister.domain.entities.validations;

import cashregister.domain.Constraint;
import cashregister.domain.entities.Entity;
import cashregister.domain.entities.validations.runtimeExceptions.GetterDoesNotExistException;
import cashregister.domain.entities.validations.runtimeExceptions.GetterDoesNotHavePublicPermission;
import cashregister.domain.entities.validations.runtimeExceptions.GetterThrewAnException;
import cashregister.domain.values.ValidationError;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class ValidatePropertyWithConstraint {
    protected List<ValidationError> errors;
    protected String property;
    protected Constraint constraint;
    protected Entity entity;

    ValidatePropertyWithConstraint(List<ValidationError> errors, String property, Constraint constraint, Entity entity) {
        this.property = property;
        this.constraint = constraint;
        this.errors = errors;
        this.entity = entity;
    }

    public void validate() {
        if (!isValid()) {
            errors.add(new ValidationError(property, constraint));
        }
    }

    protected abstract boolean isValid();

    protected Object getPropertyValue() {
        try {
            return entity.getClass().getDeclaredMethod(propertyGetterMethodName()).invoke(entity);
        } catch (NoSuchMethodException e) {
            throw new GetterDoesNotExistException(propertyGetterMethodName());
        } catch (IllegalAccessException e) {
            throw new GetterDoesNotHavePublicPermission(propertyGetterMethodName());
        } catch (InvocationTargetException e) {
            throw new GetterThrewAnException(e);
        }
    }

    protected String propertyGetterMethodName() {
        return "get" + Character.toUpperCase(property.charAt(0)) + property.substring(1);
    }
}
