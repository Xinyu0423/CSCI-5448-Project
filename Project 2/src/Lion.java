public class Lion extends Feline {
    public Lion() {
        super("Lenny",new HowlingNoise());
    }

    public Lion(String name,MakeNoise newNoiseType) {
        super(name,newNoiseType);

    }

    public String getName() {
        return this.name + " the Lion";
    }


}
