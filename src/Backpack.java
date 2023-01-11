import java.util.*;

public class Backpack {

    private HashMap<Integer,Item> items;
    private int maxCapacity;

    private int valueToFind;


    public Backpack(int maxCapacity,int valueToFind) {
        this.maxCapacity = maxCapacity;
        this.valueToFind = valueToFind;
        this.items = new HashMap<>();
    }

    public int getTotalWeight(){
        int total = 0;
        for (Item item : items.values()){
            total += item.getWeight();
        }
        return total;
    }

    public int getTotalValue(){
        int total = 0;
        for (Item item : items.values()){
            total += item.getValue();
        }
        return total;
    }

    public HashMap<Integer,Item> getItems() {
        return items;
    }

    public int size() {
        return items.size();
    }

    public Item get(Object key) {
        return items.get(key);
    }

    public void put(Integer key, Item value) {

            items.put(key, value);

    }

    public Item remove(Object key) {
        return items.remove(key);
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public Backpack clone(){
        Backpack bp = new Backpack(this.maxCapacity, this.valueToFind);
        bp.getItems().putAll(this.getItems());
        return bp;
    }

    @Override
    public String toString() {
        return "Backpack{" +
                "items=" + items.keySet() +
                ", maxCapacity=" + maxCapacity +
                ", totalWeight=" + getTotalWeight() +
                ", valueToFind=" + valueToFind +
                ", totalValue=" + getTotalValue() +
                '}';
    }
}
