package hibernateBackedRepos.repositories;

import cashregister.domain.entities.Item;
import cashregister.domain.repositories.interfaces.ItemRepository;
import org.hibernate.Session;

public class HibernateItemRepo extends HibernateEntityRepo<Item> implements ItemRepository {
    public HibernateItemRepo(Session session) {
        super("Item");
        this.session = session;
    }

    public Item findByBarcode(String barcode) {
        return findBy("barcode", barcode);
    }
}
