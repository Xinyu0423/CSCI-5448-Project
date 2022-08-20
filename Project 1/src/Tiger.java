public class Tiger extends Feline{
    public Tiger() {
        super("Tim");
    }

    public Tiger(String name) {
        super(name);
    }

    public String getName() {
        return this.name + " the Tiger";
    }
}
