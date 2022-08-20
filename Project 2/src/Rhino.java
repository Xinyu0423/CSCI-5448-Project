public class Rhino extends Pachyderm {
    public Rhino() {
        super("Robert",new SquealingNoise());

    }

    public Rhino(String name,MakeNoise newNoiseType) {
        super(name,newNoiseType);

    }

    public String getName() {
        return this.name + " the Rhino";
    }


}
