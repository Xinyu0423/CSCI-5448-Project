public class Hippo extends Pachyderm {
    public Hippo() {
        super("Henrietta");
    }

    public Hippo(String name) {
        super(name);
    }

    public String getName() {
        return this.name + " the Hippo";
    }
}
