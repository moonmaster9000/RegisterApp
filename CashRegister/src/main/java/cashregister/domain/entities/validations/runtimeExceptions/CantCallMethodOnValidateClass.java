package cashregister.domain.entities.validations.runtimeExceptions;

public class CantCallMethodOnValidateClass extends RuntimeException {
    private final String className;
    private final NoSuchMethodException e;

    public CantCallMethodOnValidateClass(String className, NoSuchMethodException e) {
        this.className = className;
        this.e = e;
    }
}
