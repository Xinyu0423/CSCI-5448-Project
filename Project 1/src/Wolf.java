public class Wolf extends Canine{
    public Wolf() {
        super("Wally");
    }

    public Wolf(String name) {
        super(name);
    }

    public String getName() {
        return this.name + " the Wolf";
    }
}
