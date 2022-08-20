
public class Cat extends Feline {

    public Cat() {
        super("Carl",new SquealingNoise());
    }

    public Cat(String name, MakeNoise newNoiseType) {
        super(name,newNoiseType);

    }

    public String getName() {
        return this.name + " the Cat";
    }

    public void eat() {
        System.out.println(getName() + " eats some fresh tuna.");
    }




}
