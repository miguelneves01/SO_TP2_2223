
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager(args[0]);

        System.out.println(manager.getSortedItems());

        Backpack lb = new Backpack();
        lb.init(manager.getSortedItems(), manager.getMaxWeight());

        ArrayList<Backpack> solutions = new ArrayList<>();
        solutions.add(lb);

        while (solutions != null) {

            int lastIndex = Collections.max(lb.getItems().keySet());
            ArrayList<Backpack> children = Backpack.generateChildren(solutions, manager,lastIndex);
            //System.out.println(children);
            ArrayList<Backpack> childrenToRemove = new ArrayList<>();
            for(Backpack bp : children){
                int ub = upperBound(bp,manager,lastIndex);
                System.out.println(ub);
                if (ub >= lb.getTotalValue()){
                    System.out.println("potential");
                    System.out.println(bp);
                    if (bp.getTotalValue() >  lb.getTotalValue()){
                        System.out.println("new best");
                        lb = bp;
                    }
                } else {
                    System.out.println("removing child");
                    childrenToRemove.add(bp);
                }
            }
            children.removeAll(childrenToRemove);
            solutions = selectSolutions(children.size()/2,children);
            //System.out.println(solutions);


        //System.out.println(children);

        }


        //System.out.println(lb);
    }

    private static ArrayList<Backpack> selectSolutions(int size,ArrayList<Backpack> children) {
        ArrayList<Backpack> solution = new ArrayList<>();
        Random random = new Random();
        for (int j = 0; j < size; j++) {
            int rand = random.nextInt(children.size());
            Backpack child = children.remove(rand);
            solution.add(child);
            //System.out.println(children.get(rand));
            //System.out.println(solution);
        }
        return solution;
    }

    private static int upperBound(Backpack bp, Manager manager, int lastIndex) {
        int w = manager.getMaxWeight() - bp.getTotalWeight();
        return ((int) Math.max(bp.getTotalValue() + Math.abs(w*manager.getSortedItems().get(lastIndex + 1).getRatio()),
                bp.getTotalValue() + Math.abs(manager.getSortedItems().get(lastIndex).getValue() - (manager.getSortedItems().get(lastIndex).getWeight() - w)
                        * manager.getSortedItems().get(lastIndex - 1).getRatio())));
    }
}