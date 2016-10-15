package cashregister.domain.entities;

public class EntityFactory {
    public UserControlledItemAttributes createUserControlledItemAttributes() {
        return new Item();
    }

    public static PersistenceControlledItemAttributes createPersistenceControlledItemAttributes() {
        return new Item();
    }
}
