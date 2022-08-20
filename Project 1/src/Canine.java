public class Canine extends Animal {

    public Canine() {
        super();
    }

    public Canine(String name) {
        super(name);
    }

    public String getName() {
        return this.name + " the Canine";
    }

    public void eat() {
        System.out.println(getName() + " has a sizeable steak for his meal.");
    }

    public void roam() {
        System.out.println(getName() + " runs around at an impressive pace.");
    }
}
