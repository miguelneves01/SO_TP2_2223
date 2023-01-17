import java.util.*;

public class Backpack {

    private HashMap<Integer,Item> items;



    public Backpack() {
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

    public void put(Integer key, Item value) {
        items.put(key, value);
    }

    public void remove(Integer key) {
        items.remove(key);
    }

    public void init(List<Item> items, int maxCapacity){
        for (int i = 0; i < items.size() &&
                (this.getTotalWeight() + items.get(i).getWeight() <= maxCapacity); i++) {
            this.put(i, items.get(i));
        }
    }

    public static Backpack clone(Backpack backpack){
        Backpack bp = new Backpack();
        bp.getItems().putAll(backpack.getItems());
        return bp;
    }

    public static ArrayList<Backpack> generateChildren(List<Backpack> backpacks, Manager manager, int lastIndex) {
        ArrayList<Backpack> children = new ArrayList<>();
        for (Backpack backpack : backpacks) {
            // remove last inserted item
            Backpack child = Backpack.clone(backpack);
            child.remove(lastIndex);
            // add items after the removed item
            for (int i = lastIndex + 1; i < manager.getSortedItems().size(); i++) {

                if (child.getTotalWeight() + manager.getSortedItems().get(i).getWeight() <= manager.getMaxWeight()) {
                    Backpack newChild = Backpack.clone(child);
                    newChild.put(i, manager.getSortedItems().get(i));
                    children.add(newChild);
                }
            }
        }
        return children;
    }



    @Override
    public String toString() {
        return "Backpack{" +
                "items=" + items.keySet() +
                ", totalWeight=" + getTotalWeight() +
                ", totalValue=" + getTotalValue() +
                '}';
    }
}
