import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Manager {

    private List<Item> items;
    private List<Item> sortedItems;
    private int itemAmount;
    private int maxValue;
    private int maxWeight;

    public Manager(String filename) {
        File inFile = new File(filename);
        try {

            Scanner sc = new Scanner(inFile);


            this.itemAmount = sc.nextInt();
            this.maxWeight = sc.nextInt();
            this.items = new ArrayList<>();
            sc.nextLine();
            for (int i = 0; i < itemAmount; i++){
                String line = sc.nextLine();
                String[] tokens = line.split(" ");

                items.add(new Item(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1])));
            }

            this.maxValue = sc.nextInt();

            sc.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        Comparator<Item> itemComparator = Comparator.comparing(o -> o.getRatio());
        sortedItems = items.stream().sorted(itemComparator.reversed()).collect(Collectors.toList());
    }

    public int getItemAmount() {
        return itemAmount;
    }


    public int getMaxValue() {
        return maxValue;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Item> getSortedItems() {
        return sortedItems;
    }
}
