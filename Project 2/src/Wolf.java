public class Wolf extends Canine {
    public Wolf() {
        super("Wally",new HowlingNoise());

    }

    public Wolf(String name,MakeNoise newNoiseType) {
        super(name,newNoiseType);

    }

    public String getName() {
        return this.name + " the Wolf";
    }


}
