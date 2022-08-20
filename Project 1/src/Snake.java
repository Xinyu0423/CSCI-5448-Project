public class Snake extends Reptile {
    public Snake() {
        super("Sam");
    }

    public Snake(String name) {
        super(name);
    }

    public String getName() {
        return this.name + " the Snake";
    }

    public void eat() {
        System.out.println(getName() + " eats a large mouse in a single gulp.");
    }

    public void roam() {
        System.out.println(getName() + " slithers around.");
    }
}
