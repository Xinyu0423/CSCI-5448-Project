public class Hippo extends Pachyderm {
    public Hippo() {
        super("Henrietta",new RoaringNoise());
    }

    public Hippo(String name,MakeNoise newNoiseType) {
        super(name,newNoiseType);

    }

    public String getName() {
        return this.name + " the Hippo";
    }


}
