package cashregister.domain.entities.validations.runtimeExceptions;

import java.lang.reflect.InvocationTargetException;

public class GetterThrewAnException extends RuntimeException {
    public GetterThrewAnException(InvocationTargetException e) {
    }
}
