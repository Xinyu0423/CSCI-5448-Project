import java.util.List;

public class ZooKeeper extends ZooEmployee {
    public ZooKeeper() {
        super();
    }

    public ZooKeeper(String name) {
        super(name);
    }

    // Call the wakeUp() method for each animal in the Zoo.
    public void wakeUpAnimals(List<Animal> animals) {
        for (int i = 0; i < animals.size(); i++) {
            System.out.println("Zookeeper wakes up " + animals.get(i).getName() + ".");
            /*
            Example of Polymorphism-
            All the animals in the list are able to be issued the same method calls regardless of their subclasses.
             */
            animals.get(i).wakeUp();
        }
    }

    // Roll call - Print each animal name in the Zoo.
    public void rollCallAnimals(List<Animal> animals) {
        System.out.println("Zookeeper performs roll call.");
        for (int i = 0; i < animals.size(); i++) {
            System.out.println("Zookeeper sees " + animals.get(i).getName() + ".");

        }
    }

    // Call the eat() method for each animal in the Zoo.
    public void feedAnimals(List<Animal> animals) {
        for (int i = 0; i < animals.size(); i++) {
            System.out.println("Zookeeper feeds " + animals.get(i).getName() + ".");
            animals.get(i).eat();
        }
    }

    // Call the roam() method for each animal in the Zoo.
    public void exerciseAnimals(List<Animal> animals) {
        for (int i = 0; i < animals.size(); i++) {
            System.out.println("Zookeeper exercises " + animals.get(i).getName() + ".");
            animals.get(i).roam();
        }
    }

    // Call the sleep() method for each animal in the Zoo.
    public void sleepAnimals(List<Animal> animals) {
        for (int i = 0; i < animals.size(); i++) {
            System.out.println("Zookeeper tells " + animals.get(i).getName() + " to go to sleep.");
            animals.get(i).sleep();
        }
    }

    // Message for Zookeeper arrival to the Zoo on a given day.
    public void arrive(int day) {
        System.out.println("Zookeeper arrives to the Zoo on Day " + day + ".");
    }

    // Message for Zookeeper departure from the Zoo on a given day.
    public void leave(int day) {
        System.out.println("Zookeeper has completed all of their tasks for Day " + day + " and leaves the Zoo.");
    }

    // Calls all methods which the Zookeeper must perform at the Zoo daily.
    public void performDailyTasks(List<Animal> animals, int day) {
        System.out.println("");
        wakeUpAnimals(animals);
        System.out.println("");
        rollCallAnimals(animals);
        System.out.println("");
        feedAnimals(animals);
        System.out.println("");
        exerciseAnimals(animals);
        System.out.println("");
        sleepAnimals(animals);
        System.out.println("");
    }
}
