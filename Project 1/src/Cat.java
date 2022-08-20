public class Cat extends Feline {

    public Cat() {
        super("Carl");
    }

    public Cat(String name) {
        super(name);
    }

    public String getName() {
        return this.name + " the Cat";
    }

    public void eat() {
        System.out.println(getName() + " eats some fresh tuna.");
    }
}
