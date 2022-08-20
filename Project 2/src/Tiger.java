public class Tiger extends Feline {
    public Tiger() {
        super("Tim",new RoaringNoise());
    }

    public Tiger(String name,MakeNoise newNoiseType) {
        super(name,newNoiseType);
    }

    public String getName() {
        return this.name + " the Tiger";
    }


}
