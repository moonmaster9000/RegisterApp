package cashregister.domain.entities.validations.runtimeExceptions;

import java.lang.reflect.InvocationTargetException;

public class ValidateClassThrewException extends RuntimeException {
    public ValidateClassThrewException(String className, InvocationTargetException e) {
    }
}
