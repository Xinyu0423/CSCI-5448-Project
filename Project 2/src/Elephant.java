public class Elephant extends Pachyderm {
    public Elephant() {
        super("Erie",new SquealingNoise());

    }

    public Elephant(String name,MakeNoise newNoiseType) {
        super(name,newNoiseType);

    }

    public String getName() {
        return this.name + " the Elephant";
    }


}
