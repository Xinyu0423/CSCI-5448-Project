// Main Animal class
public class Animal {
    /*
    Example of Identity-
    Name is a property used to distinguish an object from all other objects of the same type.
    */
    protected String name;

    // Default Constructor
    public Animal() {
        this.name = "No Name";
    }

    // Constructor to set the name
    public Animal(String name) {
        this.name = name;
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

    // Default make noise method
    public void makeNoise() {
        System.out.println(getName() + " makes noise.");
    }

    // Get the animal name (with correct suffix)
    public String getName() {
        return this.name + " the Animal";
    }

    /*
    Example of Encapsulation-
    The user cannot access the name variable directly, but must access it through the `getName` and `setName` classes.
    */
    // Set the animal name
    public void setName(String a) {
        this.name = a;
    }
}
