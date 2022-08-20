public class Reptile extends Animal {
    public Reptile() {
        super();
    }

    public Reptile(String name) {
        super(name);
    }

    public String getName() {
        return this.name + " the Reptile";
    }

    @Override
    public void makeNoise() {
        System.out.println(getName() + " squeaks and hisses in a variety of different ways.");
    }
}
