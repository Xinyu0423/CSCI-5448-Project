import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Scanner;

public class ZooKeeper extends ZooEmployee implements PropertyChangeListener {
    private List<Animal> animals;
    private int currentDay;

    public ZooKeeper() {
        super();
    }

    public ZooKeeper(String name) {
        super(name);
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    // Call the wakeUp() method for each animal in the Zoo.
    public void wakeUpAnimals() {
        support.firePropertyChange("Zookeeper", null, "wake the animals");
        for (int i = 0; i < animals.size(); i++) {
            System.out.println("Zookeeper wakes up " + animals.get(i).getName() + ".");
            animals.get(i).wakeUp();
        }
    }

    // Roll call - Print each animal name in the Zoo.
    public void rollCallAnimals() {
        support.firePropertyChange("Zookeeper", null, "roll call the animals");
        System.out.println("Zookeeper performs roll call.");
        for (int i = 0; i < animals.size(); i++) {
            System.out.println("Zookeeper sees " + animals.get(i).getName() + ".");
        }
    }

    // Call the eat() method for each animal in the Zoo.
    public void feedAnimals() {
        support.firePropertyChange("Zookeeper", null, "feed the animals");
        for (int i = 0; i < animals.size(); i++) {
            System.out.println("Zookeeper feeds " + animals.get(i).getName() + ".");
            animals.get(i).eat();
        }
    }

    // Call the roam() method for each animal in the Zoo.
    public void exerciseAnimals() {
        support.firePropertyChange("Zookeeper", null, "exercise the animals");
        for (int i = 0; i < animals.size(); i++) {
            System.out.println("Zookeeper exercises " + animals.get(i).getName() + ".");
            animals.get(i).roam();
        }
    }

    // Call the sleep() method for each animal in the Zoo.
    public void sleepAnimals() {
        support.firePropertyChange("Zookeeper", null, "put the animals to sleep");
        for (int i = 0; i < animals.size(); i++) {
            System.out.println("Zookeeper tells " + animals.get(i).getName() + " to go to sleep.");
            animals.get(i).sleep();
        }
    }

    // Call the makeNoise() method for each animal in the Zoo.
    public void makeNoiseAnimals() {
        support.firePropertyChange("Zookeeper", null, "tell the animals to make noise");
        System.out.println("Zookeeper tells the animals to make noise!");
        for (int i = 0; i < animals.size(); i++) {
            animals.get(i).makeNoise();
        }
    }

    // Message for Zookeeper arrival to the Zoo on a given day.
    public void arrive() {
        System.out.println("Zookeeper arrives to the Zoo on Day " + currentDay + ".");
        support.firePropertyChange("arrive", null, currentDay);
    }

    // Message for Zookeeper departure from the Zoo on a given day.
    public void leave() {
        System.out.println("Zookeeper has completed all of their tasks for Day " + currentDay + " and leaves the Zoo.");
        support.firePropertyChange("leave", null, currentDay);
    }

    // Zookeeper observes the ZooClock and reacts to changes
    public void propertyChange(PropertyChangeEvent evt) {
        // Update the current day number
        if (evt.getPropertyName().equals("ZooClock Current Day")) {
            currentDay = (int) evt.getNewValue();
        }
        // React to time changes from the ZooClock
        else if (evt.getPropertyName().equals("ZooClock")) {
            if (evt.getNewValue().equals(8)) {
                arrive();
            } else if (evt.getNewValue().equals(9)) {
                wakeUpAnimals();
            } else if (evt.getNewValue().equals(10)) {
                rollCallAnimals();
            } else if (evt.getNewValue().equals(12)) {
                feedAnimals();
            } else if (evt.getNewValue().equals(14)) {
                makeNoiseAnimals();
            } else if (evt.getNewValue().equals(17)) {
                exerciseAnimals();
            } else if (evt.getNewValue().equals(19)) {
                sleepAnimals();
            } else if (evt.getNewValue().equals(20)) {
                leave();
            }
        }
    }
}
