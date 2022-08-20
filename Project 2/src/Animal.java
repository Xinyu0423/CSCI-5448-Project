// Main Animal class





    /*
    Example of Identity-
    Name is a property used to distinguish an object from all other objects of the same type.
    */

public class Animal {

    protected String name;
    MakeNoise NoiseType;

    // Default Constructor
    public Animal() {
        this.name = "No Name";
        NoiseType = new DefaultNoise();
    }

    // Constructor to set the name
    public Animal(String name, MakeNoise newNoiseType) {
        this.name = name;
        NoiseType = newNoiseType;
    }

    // Default sleep method
    public void sleep() {
        System.out.println(getName() + " sleeps.");
    }

    // Default roam method
    public void roam() {
        System.out.println(getName() + " roams.");
    }

    // Default eat method
    public void eat() {
        System.out.println(getName() + " eats.");
    }

    // Default wake up method
    public void wakeUp() {
        System.out.println(getName() + " wakes up.");
    }

    // Default make noise method (now using a strategy pattern!)
    public void makeNoise() {
        System.out.print(getName() + " is ");
        NoiseType.makeNoise();
    }

    // Get the animal name (with correct suffix)
    public String getName() {
        return this.name + " the Animal";
    }

    // Set the animal name
    public void setName(String a) {
        this.name = a;
    }

    /*
    Strategy Pattern - Can set/change noise type during runtime.
    */
    public void setNoiseType(MakeNoise newNoiseType) {
       NoiseType = newNoiseType;
    }


}

