public class Elephant extends Pachyderm {
    public Elephant() {
        super("Erie");
    }

    public Elephant(String name) {
        super(name);
    }

    public String getName() {
        return this.name + " the Elephant";
    }
}
