import java.util.List;

/*
Example of Abstract Class
This is an abstract class for all Zoo Employees
*/
public abstract class ZooEmployee {
    protected String name;

    public ZooEmployee() {
        this.name = "Zippy";
    }

    public ZooEmployee(String name) {
        this.name = name;
    }

    /*
    Example of Abstraction-
    The wakeUpAnimals method is declared here as abstract, and cannot be instantiated. This method is implemented
    in another class, such as `ZooKeeper.java`.
    */
    public abstract void wakeUpAnimals(List<Animal> animals);

    public abstract void rollCallAnimals(List<Animal> animals);

    public abstract void feedAnimals(List<Animal> animals);

    public abstract void exerciseAnimals(List<Animal> animals);

    public abstract void sleepAnimals(List<Animal> animals);
}

