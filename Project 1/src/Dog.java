public class Dog extends Canine {
    public Dog() {
        super("Daniel");
    }

    public Dog(String name) {
        super(name);
    }

    public String getName() {
        return this.name + " the Dog";
    }

    // Special case method roam override
    public void roam() {
        double choice = Math.random() * 100;
        if (choice < 25) {
            System.out.println(getName() + " doesn't want to exercise, and digs a hole instead!");
        } else {
            System.out.println(getName() + " peacefully roams around.");
        }
    }
}
