public class Reptile extends Animal {
    public Reptile() {
        super();
    }

    public Reptile(String name,MakeNoise newNoiseType) {
        super(name,newNoiseType);
    }

    public String getName() {
        return this.name + " the Reptile";
    }

}
