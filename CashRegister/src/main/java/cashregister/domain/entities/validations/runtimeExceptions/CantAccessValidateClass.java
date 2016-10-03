package cashregister.domain.entities.validations.runtimeExceptions;

public class CantAccessValidateClass extends RuntimeException {
    public CantAccessValidateClass(String className, IllegalAccessException e) {

    }
}
