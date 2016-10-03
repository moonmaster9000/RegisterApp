package cashregister.domain.entities.validations.runtimeExceptions;

public class CantInstantiateValidateClass extends RuntimeException {
    public CantInstantiateValidateClass(String className, InstantiationException e) {
    }
}
