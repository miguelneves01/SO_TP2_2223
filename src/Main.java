import javafx.print.Collation;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager(args[0]);

        Comparator<Item> itemComparator = Comparator.comparing(o -> o.getRatio());
        List<Item> sortedItems = manager.getItems().stream().sorted(itemComparator.reversed()).collect(Collectors.toList());

        System.out.println(sortedItems);

        Backpack bestBp = new Backpack(manager.getMaxWeight(), manager.getMaxValue());

        for (int i = 0; i < sortedItems.size(); i++) {
            if (bestBp.getTotalWeight() + sortedItems.get(i).getWeight() > bestBp.getMaxCapacity()) {break;}
            bestBp.put(i, sortedItems.get(i));
        }


        int lowerBound = bestBp.getTotalValue();


        int curPos = Collections.max(bestBp.getItems().keySet());

        Backpack baseBp = bestBp.clone();
        int maxPos = curPos;

        while (curPos >= 0) {

            Backpack curBp1 = bestBp.clone();
            maxPos = curPos;
            while (maxPos < sortedItems.size() - 1) {

                curBp1.remove(maxPos);

                for (int i = maxPos + 1; i < sortedItems.size(); i++) {
                    if (curBp1.getTotalWeight() + sortedItems.get(i).getWeight() > curBp1.getMaxCapacity()) {
                        break;
                    }
                    curBp1.put(i, sortedItems.get(i));
                }

                if (curBp1.getTotalValue() > lowerBound) {
                    lowerBound = curBp1.getTotalValue();
                    bestBp = curBp1.clone();
                }

                maxPos = Collections.max(curBp1.getItems().keySet());

            }
            curPos--;

        }
        System.out.println(bestBp);
    }
}