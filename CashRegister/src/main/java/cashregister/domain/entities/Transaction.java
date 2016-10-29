package cashregister.domain.entities;

import java.util.ArrayList;
import java.util.List;

public class Transaction extends Entity {
    private List<Item> items = new ArrayList<>();

    public int getTotalInCents() {
        return items.stream().mapToInt(Item::getPriceInCents).sum();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        System.out.println("items is being set!" + items.toString());
        this.items = items;
    }

    public void addItem(Item item) {
        items.add(item);
    }
}
