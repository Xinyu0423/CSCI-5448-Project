import java.util.ArrayList;
import java.util.Scanner;

public class Zoo {
    public static void main(String[] args) {
        // Create Animal array for all animals at the Zoo
        ArrayList<Animal> animals = new ArrayList<>();

        // Add two of each Animal to the Zoo.
        animals.add(new Cat("Charlie"));
        animals.add(new Cat("Connor"));

        animals.add(new Dog("Darcy"));
        animals.add(new Dog("David"));

        animals.add(new Elephant("Ernie"));
        animals.add(new Elephant("Eva"));

        animals.add(new Gecko("George"));
        animals.add(new Gecko("Gale"));

        animals.add(new Hippo("Henry"));
        animals.add(new Hippo("Harrison"));

        animals.add(new Lion("Larry"));
        animals.add(new Lion("Leslie"));

        animals.add(new Rhino("Rex"));
        animals.add(new Rhino("Roger"));

        animals.add(new Snake("Slither"));
        animals.add(new Snake("Sally"));

        animals.add(new Tiger("Tom"));
        animals.add(new Tiger("Tabitha"));

        animals.add(new Wolf("Wendy"));
        animals.add(new Wolf("William"));

        // Instantiate a ZooKeeper
        ZooKeeper keeper = new ZooKeeper();

        // Ask for user input on number of days to run.
        System.out.println("Input the number of days to run the Zoo:\t");
        Scanner scanner = new Scanner(System.in);
        int days = scanner.nextInt();

        // Perform all of the ZooKeeper tasks for each day.
        for (int i = 0; i < days; i++) {
            int currentDay = i + 1;

            System.out.println("\n");
            keeper.arrive(currentDay);
            keeper.performDailyTasks(animals, currentDay);
            keeper.leave(currentDay);
        }
    }
}
