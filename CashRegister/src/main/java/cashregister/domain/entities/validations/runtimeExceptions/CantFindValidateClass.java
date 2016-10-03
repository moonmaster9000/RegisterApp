package cashregister.domain.entities.validations.runtimeExceptions;

public class CantFindValidateClass extends RuntimeException {
    private final String className;
    private final ClassNotFoundException e;

    public CantFindValidateClass(String className, ClassNotFoundException e) {
        this.className = className;
        this.e = e;
    }

    public String toString(){
        return "CantFindValidateClass: " + className;
    }
}
