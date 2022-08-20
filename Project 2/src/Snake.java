public class Snake extends Reptile {
    public Snake() {
        super("Sam",new SquealingNoise());

    }

    public Snake(String name,MakeNoise newNoiseType) {
        super(name,newNoiseType);
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
