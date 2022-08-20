public class Pachyderm extends Animal {
    public Pachyderm() {
        super();
    }

    public Pachyderm(String name) {
        super(name);
    }

    public String getName() {
        return this.name + " the Pachyderm";
    }

    // Special case method roam override
    public void roam() {
        double choice = Math.random() * 100;
        if (choice < 25) {
            System.out.println(getName() + " decides not to roam, and charges instead!");
        } else {
            System.out.println(getName() + " peacefully (albeit quite loudly) roams.");
        }
    }

    public void eat() {
        System.out.println(getName() + " grazes and eats some grass.");
    }
}
