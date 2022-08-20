public class Rhino extends Pachyderm {
    public Rhino() {
        super("Robert");
    }

    public Rhino(String name) {
        super(name);
    }

    public String getName() {
        return this.name + " the Rhino";
    }
}
