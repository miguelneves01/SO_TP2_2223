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



    // Este método deve estar junto ao método que implementa o Beam Search
    // Usar sempre a mesma lista porque eficiencia
    // Tentar implementar codigo do getChilds da foto
    // level - proximo indice a inserir

    /*
    public static void generateChildren(List<Backpack> listSol, Manager manager, int level) {
        if (level == manager.getItemAmount())
            listSol.clear();
        else {
            ArrayList<Backpack> newchildren = new ArrayList<>();
            for (Backpack listSol : backpacks) {
                // remove last inserted item

                child.remove(lastIndex);
                // add items after the removed item
                for (int i = 0; i < manag.getSortedItems().size(); i++) {

                    if (child.getTotalWeight() + manager.getSortedItems().get(i).getWeight() <= manager.getMaxWeight()) {
                        Backpack newChild = Backpack.clone(child);
                        newChild.put(i, manager.getSortedItems().get(i));
                        newchildren.add(newChild);
                    }
                }
            }
        }
    }
     */


    /*
    public static ArrayList<Backpack> generateChildren(List<Backpack> backpacks, Manager manager, int lastIndex) {
        ArrayList<Backpack> newchildren = new ArrayList<>();
        for (Backpack backpack : backpacks) {
            // remove last inserted item

            child.remove(lastIndex);
            // add items after the removed item
            for (int i = 0; i < manag.getSortedItems().size(); i++) {

                if (child.getTotalWeight() + manager.getSortedItems().get(i).getWeight() <= manager.getMaxWeight()) {
                    Backpack newChild = Backpack.clone(child);
                    newChild.put(i, manager.getSortedItems().get(i));
                    newchildren.add(newChild);
                }
            }
        }
        return newchildren;
    }
    */


    @Override
    public String toString() {
        return "Backpack{" +
                "items=" + items.keySet() +
                ", totalWeight=" + getTotalWeight() +
                ", totalValue=" + getTotalValue() +
                '}';
    }
}
