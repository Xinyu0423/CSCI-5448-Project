public class Lion extends Feline {
    public Lion() {
        super("Lenny");
    }

    public Lion(String name) {
        super(name);
    }

    public String getName() {
        return this.name + " the Lion";
    }
}
