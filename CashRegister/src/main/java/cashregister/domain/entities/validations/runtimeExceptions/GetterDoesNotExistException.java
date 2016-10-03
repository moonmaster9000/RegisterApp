package cashregister.domain.entities.validations.runtimeExceptions;

public class GetterDoesNotExistException extends RuntimeException {
    private String propertyGetter;

    public GetterDoesNotExistException(String propertyGetter) {
        this.propertyGetter = propertyGetter;
    }
}
