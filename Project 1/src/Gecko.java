public class Gecko extends Reptile {
    public Gecko() {
        super("Gary");
    }

    public Gecko(String name) {
        super(name);
    }

    public String getName() {
        return this.name + " the Gecko";
    }

    public void eat() {
        System.out.println(getName() + " eats a wonderful meal of insects.");
    }

    public void roam() {
        System.out.println(getName() + " quickly crawls up a tree branch.");
    }
}
