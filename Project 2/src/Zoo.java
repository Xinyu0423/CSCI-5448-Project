import java.util.ArrayList;
import java.util.Scanner;

public class Zoo {
    public static void main(String[] args) {
        // Create Animal array for all animals at the Zoo
        ArrayList<Animal> animals = new ArrayList<>();

        // Add two of each Animal to the Zoo.
        /*
        Strategy Pattern for making noise is applied to each Animal here.
        */
        animals.add(new Cat("Charlie", new SquealingNoise()));
        animals.add(new Cat("Connor", new SquealingNoise()));

        animals.add(new Dog("Darcy", new HowlingNoise()));
        animals.add(new Dog("David", new HowlingNoise()));

        animals.add(new Elephant("Ernie", new SquealingNoise()));
        animals.add(new Elephant("Eva", new SquealingNoise()));

        animals.add(new Gecko("George", new SquealingNoise()));
        animals.add(new Gecko("Gale", new SquealingNoise()));

        animals.add(new Hippo("Henry", new SquealingNoise()));
        animals.add(new Hippo("Harrison", new SquealingNoise()));

        animals.add(new Lion("Larry", new RoaringNoise()));
        animals.add(new Lion("Leslie", new RoaringNoise()));

        animals.add(new Rhino("Rex", new SquealingNoise()));
        animals.add(new Rhino("Roger", new SquealingNoise()));

        animals.add(new Snake("Slither", new SquealingNoise()));
        animals.add(new Snake("Sally", new SquealingNoise()));

        animals.add(new Tiger("Tom", new RoaringNoise()));
        animals.add(new Tiger("Tabitha", new RoaringNoise()));

        animals.add(new Wolf("Wendy", new HowlingNoise()));
        animals.add(new Wolf("William", new HowlingNoise()));

        // Instantiate a ZooKeeper
        ZooKeeper keeper = new ZooKeeper();
        // Provide a reference of the animal list to the Zookeeper
        keeper.setAnimals(animals);

        // Instantiate a ZooFoodServer
        ZooFoodServer server = new ZooFoodServer();

        // Instantiate a ZooAnnouncer and have it observe the ZooKeeper and ZooFoodServer
        ZooAnnouncer announcer = new ZooAnnouncer();
        /*
        Observer pattern - The ZooKeeper and ZooFoodServer are observed by the ZooAnnouncer
         */
        keeper.addPropertyChangeListener(announcer);
        server.addPropertyChangeListener(announcer);

        // Instantiate a clock, and have the ZooKeeper and ZooFoodServer observe it
        ZooClock clock = new ZooClock();
        /*
        Observer pattern - The ZooClock is observed by the ZooKeeper and ZooFoodServer
         */
        clock.addPropertyChangeListener(keeper);
        clock.addPropertyChangeListener(server);

        // Ask for user input on number of days to run.
        System.out.println("Input the number of days to run the Zoo:\t");
        Scanner scanner = new Scanner(System.in);
        int days = scanner.nextInt();

        // Loop through each day
        for (int i = 0; i < days; i++) {
            int currentDay = i + 1;
            System.out.println("\nThe Zoo has opened on Day " + currentDay + ", the ZooClock will start announcing the time.");
            // Have the Zoo clock count through all of the operating hours for a given day.
            clock.countTime(currentDay, 8, 20);
            System.out.println("The Zoo has closed on Day " + currentDay + ", the ZooClock will no longer announce the time until the Zoo opens again.\n");
        }

    }
}
