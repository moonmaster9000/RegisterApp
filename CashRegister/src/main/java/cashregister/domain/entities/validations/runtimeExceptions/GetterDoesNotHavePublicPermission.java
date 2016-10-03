package cashregister.domain.entities.validations.runtimeExceptions;

public class GetterDoesNotHavePublicPermission extends RuntimeException {
    private String propertyGetter;

    public GetterDoesNotHavePublicPermission(String propertyGetter) {
        this.propertyGetter = propertyGetter;
    }
}
