
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager(args[0]);

        System.out.println(manager.getSortedItems());

        Backpack lb = new Backpack();
        lb.init(manager.getSortedItems(), manager.getMaxWeight());
        int alfa = manager.getItemAmount() / 2;

        // Beem Search
        ArrayList<Backpack> solutions = new ArrayList<>();
        Backpack initSol = new Backpack();
        solutions.add(initSol);
        int level = 0;
        while (solutions.size() != 0) {

            //int lastIndex = Collections.max(lb.getItems().keySet());
//            ArrayList<Backpack> children = Backpack.generateChildren(solutions, manager,lastIndex);
            Backpack.generateChildren(solutions, manager,level);

            //System.out.println(children);
            ArrayList<Backpack> childrenToRemove = new ArrayList<>();
            for(Backpack bp : solutions){
                int ub = upperBound(bp,manager,level);
                System.out.println(ub);
                if (ub >= lb.getTotalValue()){
                    System.out.println("potential");
                    System.out.println(bp);
                    if (bp.getTotalValue() >  lb.getTotalValue()){
                        System.out.println("new best");
                       // lb = bp.clone();
                    }
                } else {
                    System.out.println("removing child");
                    childrenToRemove.add(bp);
                }
            }
            solutions.removeAll(childrenToRemove);
            selectSolutions(alfa,solutions);
            //System.out.println(solutions);
        //System.out.println(children);
        }


        //System.out.println(lb);
    }

    private static void selectSolutions(int alfa,ArrayList<Backpack> children) {
        Random random = new Random();
        int bpToRemove = children.size() - alfa;

        for (int j = 0; j < bpToRemove; j++) {
            int rand = random.nextInt(children.size());
            children.remove(rand);
            //System.out.println(children.get(rand));
            //System.out.println(solution);
        }
    }

    private static int upperBound(Backpack bp, Manager manager, int lastIndex) {
        int w = manager.getMaxWeight() - bp.getTotalWeight();
        return ((int) Math.max(bp.getTotalValue() + Math.abs(w*manager.getSortedItems().get(lastIndex + 1).getRatio()),
                bp.getTotalValue() + Math.abs(manager.getSortedItems().get(lastIndex).getValue() - (manager.getSortedItems().get(lastIndex).getWeight() - w)
                        * manager.getSortedItems().get(lastIndex - 1).getRatio())));
    }
}